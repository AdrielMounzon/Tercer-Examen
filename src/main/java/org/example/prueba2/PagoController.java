package org.example.prueba2;

import org.example.prueba2.TarjetaDto;
import org.example.prueba2.ResponseDto;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import com.networknt.schema.SpecVersion.VersionFlag;

import java.util.Set;

@RestController
public class PagoController implements IPagoApi {

    @Override
    @PostMapping("/pagos/tarjeta")
    public ResponseEntity<ResponseDto> procesarPago(@RequestBody TarjetaDto tarjetaDto) {
        // Validar el esquema JSON
        try {
            JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(VersionFlag.V7);
            JsonSchema schema = schemaFactory.getSchema(getClass().getClassLoader().getResourceAsStream("schemas/tarjeta.json"));
            Set<ValidationMessage> validationResult = schema.validate(tarjetaDto.toJsonNode());

            // Si hay errores de validación, devolver una respuesta de error
            if (!validationResult.isEmpty()) {
                StringBuilder errorMessage = new StringBuilder("Error de validación del esquema JSON:\n");
                for (ValidationMessage message : validationResult) {
                    errorMessage.append(message.toString()).append("\n");
                }
                return ResponseEntity.badRequest().body(new ResponseDto(400, errorMessage.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(new ResponseDto(500, "Error interno del servidor"));
        }

        // Suposición: Validamos simplemente que el número de tarjeta tenga 16 dígitos
        int statusCode = tarjetaDto.getCardNumber().length() == 16 ? 200 : 400;

        // Suposición: Si el pago se procesa correctamente, el mensaje es "successful"
        String message = statusCode == 200 ? "successful" : "Información de tarjeta inválida";

        ResponseDto response = new ResponseDto();
        response.setStatusCode(statusCode);
        response.setMessage(message);

        return ResponseEntity.ok(response);
    }
}
