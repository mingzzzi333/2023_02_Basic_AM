package com.KoreaIT.java.BasicAM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Main {
	
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		int lastArticleId=0;
		
		
		List<Article> articles = new ArrayList<>();

		while(true) {
			System.out.printf("명령어 )");
			String command = sc.nextLine().trim();
			
			if(command.length()==0) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			
			
			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article list")) {
				if(articles.size()==0) {
					System.out.println("게시글이 없습니다.");
					continue;
				}
				System.out.printf("번호/ 제목 " ); 
				String tempTitle=null;
				for(int i=articles.size()-1;i>=0;i--) {
					Article article = articles.get(i);
					
					if(article.title.length()>4) {
						tempTitle=article.title.substring(0,4);
						System.out.printf(" %d /  %s\n" , article.id, tempTitle+"..."); 
						continue;
					}
					System.out.printf(" %d /  %s\n" , article.id, article.title); 	
				}			
			}
			
			else if (command.equals("article write")) {
				String title, content; //현재 날짜 시간 String
				SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
				Date date = new Date(); //System.currentTimeMillis()
	            String regDate = formatter.format(date); // 따로 class를 만들어서 static 메서드로써 가져와서 사용 할 수 있다. -> 클래스 이름.메소드이름();
	            
	            // /** ~*/를 하면 설명을 적을수 있다.
				int id = lastArticleId+1;
				System.out.printf("제목 : ");
				title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				System.out.printf("제목 : %s, body: %s\n", title, body);
				
				Article article = new Article(id,title,body,regDate);
				
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", id);
				
				lastArticleId++;
			}
			
			else if (command.startsWith("article detail")) {
				
				String[] commandBits=command.split(" ");
				//commandBits[0] == article
				//commandBits[1] == detail
				//commandBits[2] == ~
				
				int id =Integer.parseInt(commandBits[2]);
				
				boolean found = false;
				Article foundarticle =null;
				for(int i=0;i<articles.size();i++) {
					//0, 1, 2-> index
					//1, 2, 3-> id
					Article article = articles.get(i);
					if(article.id == id) {
						found = true;
						foundarticle = article;
						break;
					}
				}
				
				if (foundarticle==null) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.printf(" 번호 : %d\n",foundarticle.id);
				System.out.printf(" 날짜 : 2023-02-02\n");
				System.out.printf(" 제목 : %s\n",foundarticle.title);
				System.out.printf(" 내용 : %s\n",foundarticle.body);
			}			
			else if (command.startsWith("article delete")) {				
				String[] commandBits=command.split(" ");
				
				int id =Integer.parseInt(commandBits[2]);
				
				Article foundarticle =null;
				int foundIndex = -1;
				
				for(int i=0;i<articles.size();i++) {
					Article article = articles.get(i);
					if(article.id == id) {
						foundarticle = article;
						foundIndex = i;
						break;
					}
				}
				
				if (foundIndex == -1) {
					System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%s번째 게시물을 삭제했습니다.\n", id);
			}

			else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
	}		
	
		
		System.out.println("==프로그램 끝==");
		
		sc.close();

	}

}
class Article{
	int id;
	String title;
	String body;
	String regDate;
	Article(int id, String title, String body, String regDate) {
		this.id=id;
		this.title=title;
		this.body=body;
		this.regDate = regDate;
	}
	
}
