package com.src.kppproject.StringInversion;

import com.src.kppproject.Conter.CounterController;
import com.src.kppproject.StringInversion.dto.ResponseStringInverseDto;
import com.src.kppproject.StringInversion.dto.StringInverseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class StringInversionController {

    public StringInversionService stringInversionService = new StringInversionService();
    CounterController calls;

    @Autowired
    public void setCalls(CounterController calls){this.calls = calls;}

    @Autowired
    private StringInvRepository stringInvRepository;

    @GetMapping("/")
    public String homeView() {
        return "home_page";
    }

    @GetMapping("/stringReverse")
    public String stringReverseView(@RequestParam(required = false, defaultValue = "") String value1,
                                    @RequestParam(required = false, defaultValue = "") String value2,
                                    Model model) throws IllegalArgumentException {
        calls.incrementCalls();

        StringInvModel stringInverseModel = stringInversionService.reverse_function(new StringInverseDto(value1, value2), stringInvRepository);

        stringInversionService.add2db(new StringInverseDto(stringInverseModel), stringInvRepository);

        model.addAttribute("value1", stringInverseModel.getString_value1());
        model.addAttribute("value2", stringInverseModel.getString_value2());

        return "home_page";
    }

    @PostMapping(
            value = "/post-stringReverse",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ResponseStringInverseDto> stringReversePostView(@RequestBody ArrayList<StringInverseDto> stringInverseDto_list) {
        calls.incrementCalls();
        return new ResponseEntity<ResponseStringInverseDto>(
                stringInversionService.listReverse(stringInverseDto_list, stringInvRepository),
                HttpStatus.OK
        );
    }

}
