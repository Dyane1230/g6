package abp.g6.g6.controller;

import abp.g6.g6.dao.UsuarioRepository;
import abp.g6.g6.model.Usuario;
import abp.g6.g6.service.UsuarioService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
// Importante: Asegúrate de importar ObjectMapper
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class LoginController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final RestTemplate restTemplate;
    @Autowired
    private UsuarioService usuarioService;

    public LoginController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Usuario usuario) {
        // Verificar usuario en la base de datos
        Usuario usuarioEncontrado = usuarioRepository.login(usuario.getEmail(), usuario.getPass());

        if (usuarioEncontrado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email o contraseña incorrectos.");
        }

        if (usuarioEncontrado.getToken() == null) {
            // Crear el JSON del usuario
            ObjectMapper objectMapper = new ObjectMapper();
            String usuarioJson;
            try {
                usuarioJson = objectMapper.writeValueAsString(usuarioEncontrado);
            } catch (JsonProcessingException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar usuario.");
            }

// Configurar los headers con autenticación básica
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBasicAuth("admin", "admin123"); // 🔥 Agregamos Basic Auth

// Crear la solicitud con el JSON y los headers
            HttpEntity<String> requestEntity = new HttpEntity<>(usuarioJson, headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    "http://localhost:8082/auth/generate", // URL de la API de seguridad
                    HttpMethod.POST,
                    requestEntity,
                    Map.class
            );

// Verificar si la solicitud fue exitosa y obtener el token
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String token = (String) response.getBody().get("token");

                // Guardar el token en la base de datos
                usuarioEncontrado.setToken(token);
                usuarioService.actualizarUsuarioById(usuarioEncontrado, usuarioEncontrado.getId());


            } else {
                return ResponseEntity.status(response.getStatusCode()).body("Error al obtener el token.");
            }

        }
        return ResponseEntity.ok(usuarioEncontrado);

    }


    public boolean isValidCertificate(X509Certificate cert) {
        try {
            // Verificar validez temporal
            cert.checkValidity();

            // Validar el emisor
            if (!isIssuerValid(cert)) {
                System.out.println("Emisor no válido");
                return false;
            }

            // Validar el número de serie (opcional)
            if (!isSerialValid(cert)) {
                System.out.println("Número de serie no válido");
                return false;
            }

            // Si todas las validaciones pasan
            return true;
        } catch (CertificateException e) {
            System.out.println("Certificado inválido: " + e.getMessage());
            return false;
        }
    }

    public boolean isIssuerValid(X509Certificate cert) {
        String trustedIssuer = "CN=AutoridadCertificadora";  // Reemplázalo por el emisor que confíes
        String issuer = cert.getIssuerDN().getName();
        return issuer.contains(trustedIssuer);
    }


    public boolean isSerialValid(X509Certificate cert) {
        String expectedSerialNumber = "1234567890ABCDEF";  // El número de serie esperado
        String certSerialNumber = cert.getSerialNumber().toString(16).toUpperCase();
        return certSerialNumber.equals(expectedSerialNumber);
    }

}

