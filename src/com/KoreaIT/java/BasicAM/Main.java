package com.KoreaIT.java.BasicAM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Main {
	
	public static void main(String[] args) {
		System.out.println("==���α׷� ����==");
		
		Scanner sc = new Scanner(System.in);
		int lastArticleId=0;
		
		
		List<Article> articles = new ArrayList<>();

		while(true) {
			System.out.printf("��ɾ� )");
			String command = sc.nextLine().trim();
			
			if(command.length()==0) {
				System.out.println("��ɾ �Է����ּ���");
				continue;
			}
			
			
			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article list")) {
				if(articles.size()==0) {
					System.out.println("�Խñ��� �����ϴ�.");
					continue;
				}
				System.out.printf("��ȣ/ ���� " ); 
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
				String title, content; //���� ��¥ �ð� String
				SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
				Date date = new Date(); //System.currentTimeMillis()
	            String regDate = formatter.format(date); // ���� class�� ���� static �޼���ν� �����ͼ� ��� �� �� �ִ�. -> Ŭ���� �̸�.�޼ҵ��̸�();
	            
	            // /** ~*/�� �ϸ� ������ ������ �ִ�.
				int id = lastArticleId+1;
				System.out.printf("���� : ");
				title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				System.out.printf("���� : %s, body: %s\n", title, body);
				
				Article article = new Article(id,title,body,regDate);
				
				articles.add(article);
				
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.\n", id);
				
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
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				System.out.printf(" ��ȣ : %d\n",foundarticle.id);
				System.out.printf(" ��¥ : 2023-02-02\n");
				System.out.printf(" ���� : %s\n",foundarticle.title);
				System.out.printf(" ���� : %s\n",foundarticle.body);
			}
			
			else if (command.startsWith("article delete")) {
				
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
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				articles.remove(id-1);
				System.out.printf("%s��° �Խù��� �����߽��ϴ�.", id);
			}

			else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�.");
			}
	}		
	
		
		System.out.println("==���α׷� ��==");
		
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
