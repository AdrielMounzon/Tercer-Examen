package org.example.prueba2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

public interface IPagoApi {

    @Tag(name = "Pagos", description = "Procesar pago con tarjeta")
    @Operation(summary = "Procesar pago", description = "Realizar un pago con tarjeta")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pago exitoso",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Solicitud incorrecta: Información de tarjeta inválida",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "No autorizado: Tarjeta no válida o fondos insuficientes",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No encontrado: Tarjeta no válida o cuenta no existente",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseDto.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Error interno del servidor: Problema en el procesamiento del pago",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = ResponseDto.class)
                                    )
                            }
                    )
            }
    )
    @PostMapping("/pagos/tarjeta")
    public ResponseEntity<ResponseDto> procesarPago(@RequestBody TarjetaDto tarjetaDto);
}
