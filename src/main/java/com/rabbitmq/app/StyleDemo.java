package com.rabbitmq.app;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.PrintService;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.Style.FontName;
import com.github.anastaciocintra.escpos.Style.FontSize;
import com.github.anastaciocintra.output.PrinterOutputStream;
import com.google.gson.Gson;
import com.rabbitmq.app.dto.ItemVendaDTOMessage;
import com.rabbitmq.app.dto.PagamentoDTOMessage;
import com.rabbitmq.app.dto.VendaDTOMessage;

import pers.pete.printer.pojo.SpaceData;

public class StyleDemo {
	SpaceData spaceData = new SpaceData();
	String linha = "";

	public static void main(String[] args) throws IOException {
		new StyleDemo().test();
	}

	public void test() throws IOException {

		String json = json();
		Gson gson = new Gson();
		VendaDTOMessage venda = gson.fromJson(json, VendaDTOMessage.class);
		PrintService printService = PrinterOutputStream.getPrintServiceByName("EPSON");
		PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
		Date dataHoraAtual = new Date();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
		String hora = new SimpleDateFormat("HH:mm:ss").format(dataHoraAtual);
		EscPos escpos = new EscPos(printerOutputStream);

		// comrpovante venda
		escpos.getStyle().setBold(true);
		escpos.getStyle().setFontName(FontName.Font_A_Default);
		escpos.getStyle().setFontSize(FontSize._1, FontSize._2);
		escpos.writeLF("             Número do pedido : " + venda.getId());
		escpos.getStyle().setBold(false);
		escpos.getStyle().reset();

		escpos.writeLF("________________________________________________");
		escpos.getStyle().setBold(false);
		escpos.writeLF("Data:" + data + "                  Hora: " + hora);
		escpos.writeLF("________________________________________________");
		escpos.getStyle().setBold(true);
		escpos.writeLF("Produtos :");
		escpos.getStyle().setBold(false);
		escpos.getStyle().setBold(true);
		escpos.writeLF("");
		escpos.writeLF("Nome              Qntd  Valor Unit    SubTotal");
		escpos.writeLF("");
		escpos.getStyle().setBold(false);
		for (ItemVendaDTOMessage i : venda.getItensVenda()) {
			escpos.writeLF("" + formatLineProduto(i));
		}
		escpos.writeLF("________________________________________________");
		escpos.getStyle().setBold(true);
		escpos.writeLF("Pagamentos :");
		escpos.writeLF("");
		escpos.writeLF("Forma de Pagamento                    Valor");
		escpos.writeLF("");
		escpos.getStyle().setBold(false);
		for (PagamentoDTOMessage p : venda.getPagamentos()) {
			escpos.writeLF("" + formatLinePagamento(p));
		}
		escpos.writeLF("");

		escpos.getStyle().setBold(true);
		escpos.getStyle().setFontName(FontName.Font_A_Default);
		escpos.getStyle().setFontSize(FontSize._1, FontSize._2);
		escpos.writeLF("                  Total do pedido : "
				+ NumberFormat.getCurrencyInstance().format(Double.valueOf(venda.getValorTotal())));
		escpos.getStyle().setBold(false);

		escpos.feed(5).cut(EscPos.CutMode.FULL);
		escpos.close();

	}

	private String formatLinePagamento(PagamentoDTOMessage p) {
		String line = p.getModoPagamentoDescricao();
		int lengthFormaPagamento = p.getModoPagamentoDescricao().length();
		if (lengthFormaPagamento > 25) {
			line = line.substring(0, 32);
			line += "     ";
		} else {
			for (int i = line.length(); i <= 36; i++) {
				line += " ";
			}
		}
		line += NumberFormat.getCurrencyInstance().format(Double.valueOf(p.getValorPagamento()));
		return line;
	}

	private String formatLineProduto(ItemVendaDTOMessage item) {
		String line = item.getProdutoNome();
		int lengthProdutoNome = item.getProdutoNome().length();

		if (lengthProdutoNome > 21) {
			line = line.substring(0, 18);
			line += "  ";
		} else {
			for (int i = line.length(); i <= 19; i++) {
				line += " ";
			}
		}
		line += item.getQuantidade();
		line += "    " + NumberFormat.getCurrencyInstance().format(Double.valueOf(item.getProdutoPreco()));

		System.out.println(line.length());
		for (int i = line.length(); i <= 36; i++) {
			line += " ";
		}
		line += NumberFormat.getCurrencyInstance().format(Double.valueOf(item.getSubTotal()));

		return line;
	}

	public String json() {
		return "{\r\n" + "  \"dataCriacaoVenda\": \"25/07/2022\",\r\n"
				+ "  \"dataFechamentoVenda\": \"25/07/2022\",\r\n" + "  \"finalizada\": \"true\",\r\n"
				+ "  \"id\": 333,\r\n" + "  \"nomeComanda\": \"kaiue\",\r\n" + "  \"subTotal\": \"70.0\",\r\n"
				+ "  \"valorTotal\": \"7000.0\",\r\n" + "  \"pagamentos\": [\r\n" + "    {\r\n"
				+ "      \"id\": \"23\",\r\n" + "      \"idModoPagamento\": \"2\",\r\n"
				+ "      \"porcentagemDesconto\": null,\r\n" + "      \"valorPagamento\": \"2222.0\",\r\n"
				+ "      \"idVenda\": \"7\",\r\n" + "      \"quantidadeParcela\": \"1\",\r\n"
				+ "      \"dataPagamento\": \"25/07/2022\",\r\n" + "      \"troco\": \"null\",\r\n"
				+ "      \"modoPagamentoDescricao\": \"PIX\"\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"id\": \"24\",\r\n" + "      \"idModoPagamento\": \"3\",\r\n"
				+ "      \"porcentagemDesconto\": null,\r\n" + "      \"valorPagamento\": \"22.0\",\r\n"
				+ "      \"idVenda\": \"7\",\r\n" + "      \"quantidadeParcela\": \"1\",\r\n"
				+ "      \"dataPagamento\": \"25/07/2022\",\r\n" + "      \"troco\": \"null\",\r\n"
				+ "      \"modoPagamentoDescricao\": \"Cartao Crédito\"\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"id\": \"25\",\r\n" + "      \"idModoPagamento\": \"4\",\r\n"
				+ "      \"porcentagemDesconto\": null,\r\n" + "      \"valorPagamento\": \"22.0\",\r\n"
				+ "      \"idVenda\": \"7\",\r\n" + "      \"quantidadeParcela\": \"1\",\r\n"
				+ "      \"dataPagamento\": \"25/07/2022\",\r\n" + "      \"troco\": \"null\",\r\n"
				+ "      \"modoPagamentoDescricao\": \"Cartão DébitoDébitoDébitoDébitoDébitoDébitoDébitoDébitoDébitoDébitoDébitoDébitoDébitoDébitoDébito\"\r\n"
				+ "    },\r\n" + "    {\r\n" + "      \"id\": \"26\",\r\n" + "      \"idModoPagamento\": \"1\",\r\n"
				+ "      \"porcentagemDesconto\": null,\r\n" + "      \"valorPagamento\": \"10.0\",\r\n"
				+ "      \"idVenda\": \"7\",\r\n" + "      \"quantidadeParcela\": \"1\",\r\n"
				+ "      \"dataPagamento\": \"25/07/2022\",\r\n" + "      \"troco\": \"6.0\",\r\n"
				+ "      \"modoPagamentoDescricao\": \"Dinheiro\"\r\n" + "    }\r\n" + "  ],\r\n"
				+ "  \"itensVenda\": [\r\n" + "    {\r\n" + "      \"id\": \"21\",\r\n"
				+ "      \"quantidade\": \"1\",\r\n" + "      \"subTotal\": \"500\",\r\n"
				+ "      \"produtoNome\": \"Bombom\",\r\n" + "      \"produtoPreco\": \"5.0\",\r\n"
				+ "      \"idVenda\": \"7\",\r\n" + "      \"idProduto\": \"2\"\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"id\": \"22\",\r\n" + "      \"quantidade\": \"1\",\r\n" + "      \"subTotal\": \"25.0\",\r\n"
				+ "      \"produtoNome\": \"Coca cola\",\r\n" + "      \"produtoPreco\": \"25.0\",\r\n"
				+ "      \"idVenda\": \"7\",\r\n" + "      \"idProduto\": \"3\"\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"id\": \"23\",\r\n" + "      \"quantidade\": \"1\",\r\n" + "      \"subTotal\": \"35.0\",\r\n"
				+ "      \"produtoNome\": \"Macarao 4queijoqueijoqueijo queijo\",\r\n"
				+ "      \"produtoPreco\": \"3555.0\",\r\n" + "      \"idVenda\": \"7\",\r\n"
				+ "      \"idProduto\": \"4\"\r\n" + "    },\r\n" + "    {\r\n" + "      \"id\": \"24\",\r\n"
				+ "      \"quantidade\": \"1\",\r\n" + "      \"subTotal\": \"5555.0\",\r\n"
				+ "      \"produtoNome\": \"Macarao Sugo\",\r\n" + "      \"produtoPreco\": \"5.0\",\r\n"
				+ "      \"idVenda\": \"7\",\r\n" + "      \"idProduto\": \"5\"\r\n" + "    }\r\n" + "  ]\r\n" + "}";

	}
}