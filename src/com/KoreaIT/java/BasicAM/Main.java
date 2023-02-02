package com.KoreaIT.java.BasicAM;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.println("==���α׷� ����==");
		
		Scanner sc = new Scanner(System.in);
		
		
		int lastArticleId=0;
		
		List<Article> articles = new ArrayList<>();

		while(true) {
			
			
			System.out.printf("��ɾ� )");
			String command = sc.nextLine();
			
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
				}else {
					System.out.println("�ִ���?");					
				}
			}
			
			else if (command.equals("article write")) {
				int id = lastArticleId+1;
				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				System.out.printf("���� : %s, body: %s\n", title, body);
				
				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.printf("%d�� ���� �����Ǿ����ϴ�.", id);
				
				lastArticleId++;
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
	
	Article(int id, String title, String body) {
		this.id=id;
		this.title=title;
		this.body=body;
	}
	
}
