package com.nighthawk.spring_portfolio.mvc.vigenere;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vigenc")
public class VigenereApiController {

    @GetMapping("all/{text}/{key}")
    public ResponseEntity<JsonNode> encryptText(@PathVariable String text, @PathVariable String key) throws JsonMappingException, JsonProcessingException {

        VigenereEncrypt encryptor = new VigenereEncrypt(text, key);
        // String encryptedText = encryptor.encrypt(text);

        // Decryptor calculator_obj = new Decryptor(text);
        // // Turn Decryptor object into JSON
        // ObjectMapper mapper = new ObjectMapper(); 
        // JsonNode json = mapper.readTree(calculator_obj.toStringJson());
  
        // return ResponseEntity.ok(json);

        // Turn encrypted text into JSON
        ObjectMapper mapper = new ObjectMapper();
        // ObjectNode node = mapper.createObjectNode();
        // node.put("result", encryptedText);
        JsonNode json = mapper.readTree(encryptor.toStringJson());

        return ResponseEntity.ok(json);
    }

}
