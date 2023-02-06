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
//=====================================================list=============================================================		
			if (command.equals("article list")) {
				if(articles.size()==0) {
					System.out.println("�Խñ��� �����ϴ�.");
					continue;
				}
				System.out.printf(" ��ȣ / ���� / ��ȸ " ); 
				String tempTitle=null;
				for(int i=articles.size()-1;i>=0;i--) {
					Article article = articles.get(i);
					
					if(article.title.length()>4) {
						tempTitle=article.title.substring(0,4);
						System.out.printf(" %4d /  %6s  /  %4d\n" , article.id, tempTitle+"...",article.hit); 
						continue;
					}
					System.out.printf(" %4d /  %6s  /  %4d\n" , article.id, article.title, article.hit); 	
				}			
			}
//=====================================================write=============================================================			
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
//=====================================================detail=============================================================			
			else if (command.startsWith("article detail")) {
				int count =0;
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
						count+=1;
						break;
					}
				}				
				if (foundarticle==null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				System.out.printf(" ��ȣ : %d\n",foundarticle.id);
				System.out.printf("��¥ : %s\n", foundarticle.regDate);
				System.out.printf("���� ��¥ : %s\n",foundarticle.updateDate);
				System.out.printf(" ���� : %s\n",foundarticle.title);
				System.out.printf(" ���� : %s\n",foundarticle.body);
				System.out.printf("��ȸ�� : %d\n", foundarticle.hit);
				
			}		
//=====================================================modify=============================================================				
			else if (command.startsWith("article modify")) {
				String[] commandBits=command.split(" ");
				
				int id =Integer.parseInt(commandBits[2]);
				
				Article foundarticle =null;
				
				for(int i=0;i<articles.size();i++) {
					Article article = articles.get(i);
					if(article.id == id) {
						foundarticle = article;
						break;
					}
				}				
				if (foundarticle==null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
	            Date date = new Date(); //System.currentTimeMillis()
	            foundarticle.updateDate = formatter.format(date);
				
				
				foundarticle.title=title;
				foundarticle.body=body;
				
				
				System.out.printf("%d�� �Խù��� �����߽��ϴ�.\n",id);
			}
//=====================================================delete=============================================================	
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
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%s��° �Խù��� �����߽��ϴ�.\n", id);
			}
//=====================================================��=============================================================
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
	int count;
	String updateDate = " ";
	int hit;
	
	Article(int id, String title, String body, String regDate ) {
		this.id=id;
		this.title=title;
		this.body=body;
		this.regDate = regDate;
		this.hit=hit;
		
	}
	
}
