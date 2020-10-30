package com.example.demo.controller;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/_placeholder")
public class PlaceholderController {

    @JacksonXmlRootElement(localName = "req")
    static class Req {
        private String string;
        private int i32;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public int getI32() {
            return i32;
        }

        public void setI32(int i32) {
            this.i32 = i32;
        }
    }

    @RequestMapping(value = "/struct", produces = MimeTypeUtils.ALL_VALUE)
    public Req struct(Req req) {
        return req;
    }
}
