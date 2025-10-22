package com.asialjim.clinc;

import com.asialjim.microapplet.common.application.App;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 诊所领域服务
 *
 * @author <a href="mailto:asialjim@hotmail.com">Asial Jim</a>
 * @version 1.0
 * @since 2025/10/22, &nbsp;&nbsp; <em>version:1.0</em>
 */
@SpringBootApplication
public class ClincDomain {
    public static void main(String[] args) {
        App.voidStart(ClincDomain.class,args);
    }
}