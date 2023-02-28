package day27.textsearch;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import day27.textsearch.repositories.CommentRepo;
import day27.textsearch.repositories.ShowsRepo;

import static day27.textsearch.configs.Constants.*;

@SpringBootApplication
public class TextSearchApplication implements CommandLineRunner {

	@Autowired
	CommentRepo cRepo;

	@Autowired
	ShowsRepo sRepo;

	public static void main(String[] args) {
		SpringApplication.run(TextSearchApplication.class, args);
	}

	// CommandLineRunner
	@Override
	public void run(String... args) throws Exception {

		List<Document> results = cRepo.searchComment("enjoyed", "hated");
		List<Document> showresults = sRepo.findShows();
		List<Document> genreresults = sRepo.countGenres();

		List<Document> histogram = sRepo.histogramOfRatings();

		// for(Document d : results) {
		// 	System.out.println(">>>" + d.toJson());
		// }

		List<Document> showRunTimeResult = sRepo.groupTvShowsByRuntime();

		List<Document> renamedResults = sRepo.getTitleAndRating();

		for(Document d : histogram) {
			System.out.println(">>>" + d.toJson());
			System.out.println("");
		}
		
	}

	

}
