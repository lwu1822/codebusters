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
    

    @GetMapping("all/{text}")
    public ResponseEntity<JsonNode> decryptText(@PathVariable String text) throws JsonMappingException, JsonProcessingException {

        VigenereEncrypt calculator_obj = new VigenereEncrypt();
      // Turn Decryptor object into JSON
      ObjectMapper mapper = new ObjectMapper(); 
      JsonNode json = mapper.readTree(calculator_obj.toStringJson());

      return ResponseEntity.ok(json);
    }

    // add other methods
}




/** Calendar API
 * Calendar Endpoint: /api/calendar/isLeapYear/2022, Returns: {"year":2020,"isLeapYear":false}
 */