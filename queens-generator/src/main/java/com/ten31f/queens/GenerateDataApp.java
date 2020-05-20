package com.ten31f.queens;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.ten31f.queens.engine.OnceBurned;
import com.ten31f.queens.repo.SolutionRepo;
import com.ten31f.queens.task.GenerateData;

/**
 * 
 * 
 * @author bmitchell
 *
 */
public class GenerateDataApp {

	private static final String OPTION_N = "n";
	private static final String OPTION_D = "d";
	private static final String OPTION_DU = "du";
	private static final String OPTION_DP = "dp";
	private static final String OPTION_U = "u";
	private static final String OPTION_P = "p";
	private static final String OPTION_T = "t";

	private static final String LONG_OPTION_N = "boardsize";
	private static final String LONG_OPTION_D = "databaseName";
	private static final String LONG_OPTION_DU = "userName";
	private static final String LONG_OPTION_DP = "password";
	private static final String LONG_OPTION_U = "url";
	private static final String LONG_OPTION_P = "port";
	private static final String LONG_OPTION_T = "threads";

	public static void main(String[] args) {

		Random random = new Random(System.nanoTime());

		Options options = new Options();

		options.addRequiredOption(OPTION_N, LONG_OPTION_N, true, "what size board");
		options.addRequiredOption(OPTION_D, LONG_OPTION_D, true, "target database name");
		options.addRequiredOption(OPTION_DU, LONG_OPTION_DU, true, "username");
		options.addRequiredOption(OPTION_DP, LONG_OPTION_DP, true, "password");
		options.addRequiredOption(OPTION_U, LONG_OPTION_U, true, "database url");
		options.addOption(OPTION_P, LONG_OPTION_P, true, "mongo port");
		options.addOption(OPTION_T, LONG_OPTION_T, true, "threads");

		CommandLineParser commandLineParser = new DefaultParser();
		try {
			CommandLine commandLine = commandLineParser.parse(options, args);

			long n = Long.parseLong(commandLine.getOptionValue(OPTION_N));
			String port = commandLine.getOptionValue(OPTION_P);

			// Engine engine = new OnceBurned(n, random);

			SolutionRepo solutionRepo = new SolutionRepo(commandLine.getOptionValue(OPTION_D),
					commandLine.getOptionValue(OPTION_DU), commandLine.getOptionValue(OPTION_DP),
					commandLine.getOptionValue(OPTION_U),
					(port == null) ? SolutionRepo.DEFAULT_MONGO_PORT : Integer.parseInt(port));

			int threadCount = (commandLine.getOptionValue(OPTION_T) != null)
					? Integer.parseInt(commandLine.getOptionValue(OPTION_T))
					: 1;

			ExecutorService executorService = Executors.newCachedThreadPool();
			for (int i = 0; i < threadCount; i++)
				executorService.execute(new GenerateData(n, new OnceBurned(n, random), solutionRepo));

			executorService.shutdown();

			try {
				while (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException parseException) {
			System.err.println("Parsing failed.  Reason: " + parseException.getMessage());
			HelpFormatter helpFormatter = new HelpFormatter();
			helpFormatter.printHelp("ls", options);
		}

	}
}
