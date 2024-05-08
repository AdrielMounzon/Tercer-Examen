package org.example.prueba2;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;

@RestController
public class LoginController implements ILoginApi {

    @Autowired
    private LoginService loginService;

    @Override
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(loginDto);
            JsonSchemaFactory factory = JsonSchemaFactory.getInstance(com.networknt.schema.SpecVersion.VersionFlag.V7);
            JsonSchema jsonSchema = factory.getSchema(LoginController.class.getClassLoader().getResourceAsStream("schemas/usuario.json"));
            JsonNode jsonNode = mapper.readTree(json);
            Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);

            StringBuilder errorsCombined = new StringBuilder();
            for (ValidationMessage error : errors) {
                errorsCombined.append(error.toString()).append("\n");
            }

            if (!errors.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            // Continuar con la autenticación si la validación es exitosa
            TokenDto tokenDto = loginService.authenticate(loginDto);
            return ResponseEntity.ok(tokenDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.ok().build();
        }
    }
}
