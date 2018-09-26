package com.wpisen.trace.server.service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Ignore;
import org.junit.Test;

import com.wpisen.trace.server.model.ClientSessionVo;

public class SerializableTest {
	@Ignore
    @Test
    public void serializationTest() throws IOException {
        ClientSessionVo se = new ClientSessionVo();
        se.setAppId("appId");
        se.setSessionId("我是一个中文");
        se.setClientMd5("MD@@@@@");
        se.setLoginTime(System.currentTimeMillis());
        Path f = Files.createTempFile("ser", ".data");
        OutputStream put = new FileOutputStream(f.toFile());
        @SuppressWarnings("resource")
		ObjectOutputStream out = new ObjectOutputStream(put);
        out.writeObject(se);
        System.out.println(f);
    }
	@Ignore
    @Test
    public void deserialization() throws IOException, ClassNotFoundException {
        InputStream put = new FileInputStream("C:/Users/ADMINI~1/AppData/Local/Temp/ser5143975518165619612.data");
        @SuppressWarnings("resource")
		ObjectInputStream input=new ObjectInputStream(put);
        ClientSessionVo obj = (ClientSessionVo) input.readObject();
        System.out.println(obj.getAppId());
    }

}
