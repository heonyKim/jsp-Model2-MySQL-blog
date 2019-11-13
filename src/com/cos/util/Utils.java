package com.cos.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import com.cos.model.Board;

public class Utils {
	public static final List<String> readCounts = new ArrayList<String>();
	// �̸����� ������ �����ϱ�
	public static void setPreviewContent(List<Board> boards) {

		for (Board board : boards) {

			Document doc = Jsoup.parse(board.getContent()); // ���ϴ� String�� HTML �������� ����ȭ
			Elements ets = doc.select("img");

			if (ets != null) {
				for (Element et : ets) {
					et.remove();
				}
			}
			board.setContent(doc.toString());
		}

	}

	// �̸����� �̹��� �����ϱ�

	public static void setPreviewImg(List<Board> boards) {

		for (Board board : boards) {

			Document doc = Jsoup.parse(board.getContent());

			Element uet = doc.selectFirst("a");
			Element et = doc.selectFirst("img");

			if (et != null) {

				String previewImg = et.attr("src"); // https://~

				board.setPreviewImg(previewImg);

			} else if (uet != null) {
				String href = uet.attr("href");
				if (href.contains("https://www.youtube.com/watch")) {
					String video[] = href.split("=");
					String v = video[1];
					String youtubeThumbnail = "https://img.youtube.com/vi/" + v + "/hqdefault.jpg";
					board.setPreviewImg(youtubeThumbnail);
				}

			} else {
				board.setPreviewImg("/blog/img/home-blog/blog-1.jpg");
			}
		}

	}

	// ��Ʃ�� �̸�����ȭ�� �����ϱ�
	public static void setPreviewYoutube(Board board) {

		Document doc = Jsoup.parse(board.getContent());
//		System.out.println(doc);
		Elements ets = doc.select("a");

		if (ets != null) {
			for (Element et : ets) {
				String href = et.attr("href");
//				System.out.println("href : " + href);
				if (href.contains("https://www.youtube.com/watch") && !et.text().equals("")) {
					String video[] = href.split("=");
					String v = video[1];
//					System.out.println(v);

					String iframe = "<br><iframe src = 'https://www.youtube.com/embed/" + v + "' "
							+ "width='600px' height='350px' allowfullscreen>";
//					System.out.println(iframe);

					et.after(iframe);
//					System.out.println(et.after(doc));

				}
				board.setContent(doc.toString());// board �ϼ�
			}
		} // end of if

	}// end of function

	@Test
	public void previewYoutubeTest() {

		String content = "<a href = 'https://www.youtube.com/watch?v=ysAnT3QBLZE'>https://www.youtube.com/watch?v=ysAnT3QBLZE</a>";
		Document doc = Jsoup.parse(content);
//		System.out.println(doc);
		Elements ets = doc.select("a");

		if (ets != null) {
			for (Element et : ets) {
				String href = et.attr("href");


//				System.out.println("href : " + href);
				if (href.contains("https://" + "www.youtube.com/watch")) {
					String video[] = href.split("=");
					String v = video[1];
//					System.out.println(v);
					String iframe = "<br><iframe src = 'https://www.youtube.com/embed/" + v + "' "
							+ "width='600px' height='350px' allowfullscreen>";
//					System.out.println(iframe);
					et.after(iframe);

				}

			}
		} // end of if

	}// end of function

}
