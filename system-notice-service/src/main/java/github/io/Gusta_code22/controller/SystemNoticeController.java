package github.io.Gusta_code22.controller;

import github.io.Gusta_code22.Enum.Level;
import github.io.Gusta_code22.config.SystemNoticeConfig;
import github.io.Gusta_code22.model.SystemNotice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController

public class SystemNoticeController {

    private static final AtomicLong Counter = new AtomicLong();


    @Autowired
    private SystemNoticeConfig config;

    @RequestMapping("/systemnotice")
    public SystemNotice notice(@RequestParam(name = "nome", defaultValue = "") String nome){
        if (nome.isEmpty()){
            nome = config.defaultValue();

        }
        return new SystemNotice(Counter.incrementAndGet(), config.level(),
                String.format(config.message(), nome));
    }
}
