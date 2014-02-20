package infarmedvalidate;

import java.io.IOException;

import java.text.Normalizer;
import java.util.Map;

import javax.jws.WebService;

import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


@WebService(portName = "InfarmedSoap12HttpPort")
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
public class Infarmed {
	
	public String validateDrugs(String subst, String nome, String dosagem) throws IOException, InterruptedException {

		//Thread.sleep(5000);
		
		String s = Normalizer.normalize(subst, Normalizer.Form.NFD);
		s = s.replaceAll("[^\\p{ASCII}]", "");
		
		String n = Normalizer.normalize(nome, Normalizer.Form.NFD);
		n = n.replaceAll("[^\\p{ASCII}]", "");
		
		String d = Normalizer.normalize(dosagem, Normalizer.Form.NFD);
		d = d.replaceAll("[^\\p{ASCII}]", "");

		org.jsoup.Connection.Response res = Jsoup.connect("http://www.infarmed.pt/infomed/login.php")
				.data("u_nome", "infomed", "u_pass", "infomed")
				.method(Method.POST)
				.execute();

		Map<String, String> cookies = res.cookies();

		Document doc_search = Jsoup.connect("http://www.infarmed.pt/infomed/pesquisa.php")
				.cookies(cookies)
				.get();

		Document doc_result = Jsoup.connect("http://www.infarmed.pt/infomed/lista.php")
				.data("dci", s)
				.data("nome_comer", n)
				.data("dosagem", d)
				.cookies(cookies)
				.post();

		Elements results = doc_result.getElementsByTag("div");

		Element e = results.get(4);
		System.out.println(e.toString());
		String siteString = e.text();

		if (!siteString.contains("resultados!")){
			return "VALIDO!";
			
		} else {
			return "INVALIDO!";
		}
	}
}
