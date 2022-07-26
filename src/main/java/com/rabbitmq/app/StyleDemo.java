package com.rabbitmq.app;

import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import pers.pete.printer.ThermalPrinter;
import pers.pete.printer.consts.Const;
import pers.pete.printer.enums.Align;
import pers.pete.printer.pojo.BaseData;
import pers.pete.printer.pojo.ImageData;
import pers.pete.printer.pojo.LineData;
import pers.pete.printer.pojo.QrcodeData;
import pers.pete.printer.pojo.RowData;
import pers.pete.printer.pojo.SpaceData;
import pers.pete.printer.pojo.TitleData;
import pers.pete.printer.pojo.WordData;

public class StyleDemo {

	public static void main(String[] args) {
		new StyleDemo().test();
	}

	public void test() {
		ThermalPrinter a = new ThermalPrinter(240 , true);

		
		List<BaseData> list = new ArrayList<>();
		list.add(new WordData("ID: 1001", Align.RIGHT, Font.BOLD, 5));
		list.add(new TitleData("Comprovante de Venda"));
		list.add(new LineData(true));
        list.add(new WordData("Número Pedido: 55555", Align.CENTER, Font.PLAIN, Const.FONTSIZE_10));
        list.add(new WordData("     Data:19/12/2022                          Hora: 22:22",  Align.LEFT, Font.PLAIN, Const.FONTSIZE_10));
        list.add(new LineData("Produtos", false));
        list.add(new WordData("      Nome                     qtd.        Vr. unt                   SubTotal  ", Align.LEFT, Font.BOLD, Const.FONTSIZE_8));
        list.add(new LineData());
        list.add(new WordData("      Macarrão  4Quei     2        R$ 20,55                  R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
        list.add(new SpaceData());
        list.add(new WordData("      Macarrão  4Quei     2        R$ 20,55                  R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
        list.add(new SpaceData());
        list.add(new WordData("      Macarrão  4Quei     "
        		+ "2        R$ 20,55                  R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
        list.add(new SpaceData());
        list.add(new WordData("      Macarrão  4Quei     2        R$ 20,55                  R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
        list.add(new SpaceData());
        list.add(new WordData("      Macarrão  4Quei     2        R$ 20,55                  R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
        list.add(new SpaceData());
        list.add(new WordData("      Macarrão  4Quei     2        R$ 20,55                  R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
        list.add(new SpaceData());
        list.add(new WordData("      Macarrão  4Quei     2        R$ 20,55                  R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
        list.add(new SpaceData());
         list.add(new LineData("Pagamento ", false));
          list.add(new WordData("      Forma de Pagamento                                         Valor ", Align.LEFT, Font.BOLD, Const.FONTSIZE_8));
          list.add(new WordData("         Dinheiro                                                          R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
          list.add(new WordData("         Cartão Crédito                                               R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
          list.add(new WordData("         Cartão Débito                                                R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
          list.add(new WordData("         PIX                                                                 R$ 41,10  ", Align.LEFT, Font.PLAIN, Const.FONTSIZE_8));
          list.add(new WordData("                             Total do pedido: R$ 900,00", Align.LEFT, Font.BOLD, Const.FONTSIZE_10));
          list.add(new WordData("                                        Troco: R$ 10,00", Align.RIGHT, Font.BOLD, Const.FONTSIZE_10));

        
        
        
        

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