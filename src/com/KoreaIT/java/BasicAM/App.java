package com.KoreaIT.java.BasicAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.BasicAM.dto.Article;
import com.KoreaIT.java.BasicAM.util.Util;

public class App {
	public static List<Article> articles;

	static {
		articles = new ArrayList<>();
	}

	public void run() {
		System.out.println("==���α׷� ����==");

		makeTestData();

		Scanner sc = new Scanner(System.in);

		while (true) {

			System.out.printf("��ɾ� ) ");
			String command = sc.nextLine().trim();

			if (command.length() == 0) {
				System.out.println("��ɾ �Է����ּ���");
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article list")) {
				if (articles.size() == 0) {
					System.out.println("�Խñ��� �����ϴ�");
					continue;
				}
				System.out.println("��ȣ    /      ����     /     ��ȸ    ");
				String tempTitle = null;
				for (int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					if (article.title.length() > 4) {
						tempTitle = article.title.substring(0, 4);
						System.out.printf("%4d	/    %6s    /   %4d\n", article.id, tempTitle + "...", article.hit);
						continue;
					}

					System.out.printf("%4d	/    %6s    /   %4d\n", article.id, article.title, article.hit);
				}
			} else if (command.equals("article write")) {
				int id = articles.size() + 1;
				System.out.printf("���� : ");
				String regDate = Util.getNowDateStr();
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();

				Article article = new Article(id, regDate, regDate, title, body);
				articles.add(article);

				System.out.printf("%d�� ���� ���� �Ǿ����ϴ�\n", id);
			} else if (command.startsWith("article detail ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				foundArticle.increaseHit();
				System.out.printf("��ȣ : %d\n", foundArticle.id);
				System.out.printf("�ۼ���¥ : %s\n", foundArticle.regDate);
				System.out.printf("������¥ : %s\n", foundArticle.updateDate);
				System.out.printf("���� : %s\n", foundArticle.title);
				System.out.printf("���� : %s\n", foundArticle.body);
				System.out.printf("��ȸ : %d\n", foundArticle.hit);

			} else if (command.startsWith("article modify ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundArticle = article;
						break;
					}
				}

				if (foundArticle == null) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}

				System.out.printf("���� : ");
				String title = sc.nextLine();
				System.out.printf("���� : ");
				String body = sc.nextLine();
				String updateDate = Util.getNowDateStr();

				foundArticle.title = title;
				foundArticle.body = body;
				foundArticle.updateDate = updateDate;

				System.out.printf("%d�� �Խù��� �����߽��ϴ�\n", id);

			} else if (command.startsWith("article delete ")) {
				String[] commandBits = command.split(" ");

				int id = Integer.parseInt(commandBits[2]);

				int foundIndex = -1;

				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					if (article.id == id) {
						foundIndex = i;
						break;
					}
				}

				if (foundIndex == -1) {
					System.out.printf("%d�� �Խù��� �������� �ʽ��ϴ�.\n", id);
					continue;
				}
				articles.remove(foundIndex);
				System.out.printf("%d�� �Խù��� �����߽��ϴ�\n", id);

			}

			else {
				System.out.println("�������� �ʴ� ��ɾ��Դϴ�");
			}

		}

		System.out.println("==���α׷� ��==");

		sc.close();

	}

	static void makeTestData() {
		System.out.println("�׽�Ʈ�� ���� �����͸� �����մϴ�");

		articles.add(new Article(1, Util.getNowDateStr(), Util.getNowDateStr(), "����1", "����1", 11));
		articles.add(new Article(2, Util.getNowDateStr(), Util.getNowDateStr(), "����2", "����2", 22));
		articles.add(new Article(3, Util.getNowDateStr(), Util.getNowDateStr(), "����3", "����3", 33));
	}
}