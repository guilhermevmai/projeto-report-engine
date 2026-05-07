package com.report_engine.api.config.converters;

import com.report_engine.api.model.enums.ReadFilesStrategies;
import org.springframework.core.convert.converter.Converter;

public class StringToReadFilesStrategiesConverter  implements Converter<String, ReadFilesStrategies> {

    @Override
    public ReadFilesStrategies convert(String source) {
        return ReadFilesStrategies.valueOf(source.toUpperCase());
    }
}
