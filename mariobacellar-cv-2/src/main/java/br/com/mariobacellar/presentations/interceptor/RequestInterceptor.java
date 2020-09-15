package br.com.mariobacellar.presentations.interceptor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

public class RequestInterceptor implements HandlerInterceptor {
	
	public static int count;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		String[] arrCookies = new String [request.getCookies().length];
				
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) 
			arrCookies[i]=cookies[i].getValue();
				
		List<String> 
		lt  = new ArrayList<String>();
		lt.add(""+LocalDateTime.now());
		lt.add(request.getLocalAddr() );
		lt.add(request.getLocalName() );
		lt.add(request.getRemoteAddr());
		lt.add(request.getRemoteHost());
		lt.add(request.getRemoteUser());
		lt.add(request.getServerName());
		lt.add(request.getPathInfo());
		
		saveReqInfo(lt,arrCookies);
		
		saveDb(lt,arrCookies);
		
		sendEmail(lt,arrCookies);
		
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	private void sendEmail(List<String> lt, String[] arrCookies) {
		// TODO Auto-generated method stub
		
	}

	private void saveDb(List<String> lt, String[] arrCookies) {
		// TODO Auto-generated method stub
		
	}

	private void saveReqInfo(List<String> lt, String[] arrCookies) {

		String fileName= "./src/main/resources/request.txt";
		BufferedWriter writer;
		int i=0;
		count = lastVisitNumber();

		try {
			writer = new BufferedWriter(new FileWriter( fileName, true ));

			System.out.println("--------------------------------------------------------------------");
			writer.write      ("--------------------------------------------------------------------\n");
			
			System.out.println("* request......[" +(count++) +"] at ["+lt.get(0)+"]");
			writer.write      ("* request......[" +(count++) +"] at ["+lt.get(0)+"]\n");

			System.out.println("* localAddr....[" + lt.get(1) + "]");
			writer.write      ("* localAddr....[" + lt.get(1) + "]\n");
			
			System.out.println("* localName... [" + lt.get(2) +"]");
			writer.write      ("* localName... [" + lt.get(2) +"]\n");
			
			System.out.println("* remoteAddr...[" + lt.get(3) +"]");
			writer.write      ("* remoteAddr...[" + lt.get(3) +"]\n");
			
			System.out.println("* remoteHost...[" + lt.get(4) +"]");
			writer.write      ("* remoteHost...[" + lt.get(4) +"]\n");

			System.out.println("* remoteUser...[" + lt.get(5) +"]");
			writer.write      ("* remoteUser...[" + lt.get(5) +"]\n");

			System.out.println("* serverName...[" + lt.get(6) +"]");
		    writer.write      ("* remoteUser...[" + lt.get(6) +"]\n");

		    System.out.println("* pathInfo.....[" + lt.get(7) +"]");
			writer.write      ("* pathInfo.....[" + lt.get(7) +"]\n");
				
			for (String srt: arrCookies) {
			System.out.println("* cookie.......[" + srt +"]");
			writer.write      ("* cookie.......[" + srt +"]\n");
			}

			System.out.println("--------------------------------------------------------------------");
			writer.write      ("--------------------------------------------------------------------\n");
				
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

    private int lastVisitNumber() {
    	int				ret		= 0;
    	String			fileName= "./src/main/resources/visits.txt";
    	List<String>	list	= new ArrayList<String>();
	
    	try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			list = br.lines().collect(Collectors.toList());
			
			ret = Integer.parseInt( list.get(0));
			ret +=1;
			
		    BufferedWriter 
		    writer = new BufferedWriter(new FileWriter( fileName ));
		    writer.write(""+ret);
		    writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    	return ret;
    }
	
	
}
