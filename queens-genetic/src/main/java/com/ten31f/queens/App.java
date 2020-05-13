package com.ten31f.queens;

import java.util.Random;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.ten31f.queens.engine.Engine;
import com.ten31f.queens.engine.OnceBurned;
import com.ten31f.queens.task.GenerateData;

/**
 * 
 * 
 * @author bmitchell
 *
 */
public class App {

	private static final String OPTION_N = "n";
	private static final String LONG_OPTION_N = "boardsize";

	public static void main(String[] args) {

		Random random = new Random(System.nanoTime());

		Options options = new Options();

		options.addOption(OPTION_N, LONG_OPTION_N, true, "what size board");

		CommandLineParser commandLineParser = new DefaultParser();
		try {
			CommandLine commandLine = commandLineParser.parse(options, args);

			long n = Long.parseLong(commandLine.getOptionValue(OPTION_N));

			Engine engine = new OnceBurned(n, random);

			GenerateData generateData = new GenerateData(n, engine);

			generateData.run();

		} catch (ParseException parseException) {
			System.err.println("Parsing failed.  Reason: " + parseException.getMessage());
		}

	}
}
