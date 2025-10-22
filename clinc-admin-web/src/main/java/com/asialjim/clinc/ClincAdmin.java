package com.asialjim.clinc;

import com.asialjim.microapplet.common.application.App;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 后管服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/22, &nbsp;&nbsp; <em>version:1.0</em>
 */
@SpringBootApplication
public class ClincAdmin {
    public static void main(String[] args) {
        App.voidStart(ClincAdmin.class,args);
    }
}