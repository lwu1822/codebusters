package com.nighthawk.spring_portfolio.mvc.vigenere;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// import javax.servlet.http.HttpServletRequest;

// import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/vigenc")
public class VigenereApiController {

    @GetMapping("all/{text}/{key}")
    public ResponseEntity<JsonNode> encryptText(@PathVariable String text, @PathVariable String key) throws JsonMappingException, JsonProcessingException {

        VigenereEncrypt encryptor = new VigenereEncrypt(key);
        String encryptedText = encryptor.encrypt(text);

        // Turn encrypted text into JSON
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("result", encryptedText);
        JsonNode json = mapper.convertValue(node, JsonNode.class);

        return ResponseEntity.ok(json);
    }

    // add other methods
}




/** Calendar API
 * Calendar Endpoint: /api/calendar/isLeapYear/2022, Returns: {"year":2020,"isLeapYear":false}
 */