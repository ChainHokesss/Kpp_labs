package com.src.kppproject.StringInversion;
import com.src.kppproject.Cache.CacheStringInversion;
import com.src.kppproject.StringInversion.dto.ResponseStringInverseDto;
import com.src.kppproject.StringInversion.dto.StringInverseDto;
import org.apache.logging.log4j.Level;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.src.kppproject.appLogger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;



@Component
@Service
public class StringInversionService {

    private CacheStringInversion cacheStringInversion = new CacheStringInversion();

    public String reverse(String str) {
        if(str.equals("")){
            appLogger.setLog(Level.ERROR, "Illegal arguments in StringInversion: empty");
            throw new IllegalArgumentException("Illegal arguments in StringInversion: empty");
        }

        if(cacheStringInversion.isCached(str)){
            return cacheStringInversion.getCachedValue(str);
        }

        char[] array = str.toCharArray();
        String result = "";
        for (int i = array.length - 1; i >= 0; i--) {
            result = result + array[i];
        }

        cacheStringInversion.addValueToCache(str, result);
        return result;
    }

    @Async
    public StringInvModel reverse_function(StringInverseDto stringInverseDto, StringInvRepository stringInvRepository){
        String string_value1;
        String string_value2;

        if(!stringInverseDto.getString_value1().equals("")){
            string_value1 = stringInverseDto.getString_value1();
            string_value2 = reverse(stringInverseDto.getString_value1());
        }else{
            string_value1 = reverse(stringInverseDto.getString_value2());
            string_value2 = stringInverseDto.getString_value2();
        }

        StringInverseDto responseDto = new StringInverseDto(string_value1, string_value2);
        return add2db(responseDto, stringInvRepository);
    }


    public ResponseStringInverseDto listReverse(ArrayList<StringInverseDto> stringInverseDto_list, StringInvRepository stringInvRepository){
        ArrayList<StringInverseDto> responseStringInverseDto_list = new ArrayList<StringInverseDto>();
        for(StringInverseDto stringInverseDto: stringInverseDto_list){
            StringInvModel stringInvModel = reverse_function(stringInverseDto, stringInvRepository);
            responseStringInverseDto_list.add(new StringInverseDto(stringInvModel));
        }

        ResponseStringInverseDto responseStringInverseDto = new ResponseStringInverseDto();

        responseStringInverseDto.stringInverseDto = responseStringInverseDto_list;

        responseStringInverseDto.number = responseStringInverseDto.stringInverseDto.size();

        StringInverseDto max_in_value1 = responseStringInverseDto.stringInverseDto.stream().map(v -> v).max(Comparator.comparingInt(s -> s.getString_value1().length())).get();
        StringInverseDto min_in_value1 = responseStringInverseDto.stringInverseDto.stream().map(v -> v).min(Comparator.comparingInt(s -> s.getString_value1().length())).get();
        responseStringInverseDto.maxlen_str = max_in_value1.getString_value1();
        responseStringInverseDto.minlen_str = min_in_value1.getString_value1();
        return responseStringInverseDto;
    }

    public StringInvModel add2db(StringInverseDto stringInverseDto, StringInvRepository stringInvRepository){
        StringInvModel stringInvModel = new StringInvModel(
                stringInverseDto.getString_value1(),
                stringInverseDto.getString_value2()
        );

        Integer id = getId(stringInverseDto, stringInvRepository);
        if (id != null) {
            stringInvModel.setId(id);
            return stringInvModel;
        }
        stringInvRepository.save(stringInvModel);
        return stringInvModel;
    }

    public Integer getId(StringInverseDto stringInverseDto, StringInvRepository stringInvRepository){
        Iterable<StringInvModel> stringInvModelList = stringInvRepository.findAll();
        for(StringInvModel ex_StringInvModel: stringInvModelList){
            if(
                    stringInverseDto.getString_value1().equals(ex_StringInvModel.getString_value1())
                            && stringInverseDto.getString_value2().equals(ex_StringInvModel.getString_value2())
            ){
                return ex_StringInvModel.getId();
            }
        }
        return null;
    }

    public Optional<StringInvModel> getById(Integer model_id, StringInvRepository stringInvRepository){
        if(stringInvRepository.existsById(model_id)) {
            return stringInvRepository.findById(model_id);
        }
        return null;
    }
    public Integer getSize(StringInvRepository stringInvRepository){
        Iterable<StringInvModel> stringInvModelList = stringInvRepository.findAll();
        return Math.toIntExact(stringInvRepository.count());
    }
}
