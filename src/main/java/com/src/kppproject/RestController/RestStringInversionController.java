package com.src.kppproject.RestController;

import com.src.kppproject.StringInversion.StringInvModel;
import com.src.kppproject.StringInversion.StringInvRepository;
import com.src.kppproject.StringInversion.StringInversionService;
import com.src.kppproject.StringInversion.dto.StringInverseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RestStringInversionController {
    public StringInversionService stringInversionService = new StringInversionService();
    @Autowired
    private StringInvRepository stringInvRepository;

    @GetMapping("/async-add")
    public Integer AsyncAdd(@RequestParam(required = false, defaultValue = "") String value1,
                            @RequestParam(required = false, defaultValue = "") String value2)
            throws IllegalArgumentException{

        StringInvModel stringInverseModel = stringInversionService.reverse_function(new StringInverseDto(value1, value2), stringInvRepository);

        return stringInverseModel.getId();
    }
    @GetMapping("/get/{id}")
    public Optional<StringInvModel> getStringInversion(@PathVariable(value = "id") Integer id){
       return stringInversionService.getById(id, stringInvRepository);
    }
}
