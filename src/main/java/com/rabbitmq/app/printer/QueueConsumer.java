package com.rabbitmq.app.printer;

import java.awt.Font;
import java.awt.print.PrinterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.app.util.BusinessException;

import pers.pete.printer.ThermalPrinter;
import pers.pete.printer.consts.Const;
import pers.pete.printer.enums.Align;
import pers.pete.printer.pojo.BaseData;
import pers.pete.printer.pojo.LineData;
import pers.pete.printer.pojo.QrcodeData;
import pers.pete.printer.pojo.RowData;
import pers.pete.printer.pojo.SpaceData;
import pers.pete.printer.pojo.TitleData;
import pers.pete.printer.pojo.WordData;


@Component
public class QueueConsumer {

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(String message) throws BusinessException {
        System.out.println("Message " + message + "  " + LocalDateTime.now());

        ThermalPrinter a = new ThermalPrinter(145, true);
        
        List<BaseData> list = new ArrayList<>();

        list.add(new WordData("ID: 1001", Align.RIGHT, Font.BOLD, 5));

        list.add(new TitleData("Comprovante de Venda"));
        list.add(new LineData(true));
//
//        list.add(new LineData("Test", false));
//        list.add(new WordData("TestTestTestTestTestTestTestTestTest", Align.LEFT));
//        list.add(new LineData());
//        list.add(new WordData("TestTestTestTestTestTestTestTestTest", Align.CENTER, Font.PLAIN, Const.FONTSIZE_10));
//        list.add(new LineData());
//        list.add(new WordData("TestTestTestTestTestTestTestTestTest", Align.RIGHT, Font.ITALIC, Const.FONTSIZE_8));
//        list.add(new SpaceData());
//
//        list.add(new LineData("TestTestTest", false));
//        list.add(new RowData("TestTestTest", "1001"));
//        list.add(new RowData("TestTest", "TestTestTest"));
//        list.add(new RowData("TestTestTest", "Test"));
//        list.add(new RowData("TestTestTest", "2019-06-20"));
//        list.add(new RowData("Test", "2019-06-21"));
//        list.add(new RowData("Test", ""));
//        list.add(new RowData("Test", "Test"));
//
//        list.add(new LineData("Test", false));
//        list.add(new QrcodeData("https://www.baidu.com/s?wd=Wow!%20You%20find%20me!"));
//        list.add(new LineData("TestTest", false));

//        File file = new File("D:\\test.png");
//        InputStream is;
//        BufferedImage bi = null;
//        try {
//          is = new FileInputStream(file);
//          bi = ImageIO.read(is);
//        } catch (IOException e) {
//          e.printStackTrace();
//        }
//        list.add(new ImageData(bi));
//
//        list.add(new SpaceData(10));
//        list.add(new WordData("打印时间: 2019-06-21", Align.RIGHT, Font.BOLD, 5));

        try {
          a.print(list);
        } catch (PrinterException e) {
          e.printStackTrace();
        }
      }
    	
        
    

}