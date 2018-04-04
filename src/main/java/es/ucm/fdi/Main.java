package es.ucm.fdi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import es.ucm.fdi.ini.*;
import es.ucm.fdi.control.*;

/**
 * IniError class.
 *
 * @author Samir Genaim genaim@gmail.com
 * @author Borja Lozano (Slight modifications)
 */
public class Main {
	
	private final static Integer _timeLimitDefaultValue = 10;
	private static Integer _timeLimit = null;
	private static String _inFile = null;
	private static String _outFile = null;
	/**
	 * Reads the arguments provided and parses the commands.
	 * @param args arguments provided in the execution of the program
	 */
	private static void parseArgs(String[] args) {
		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();
		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseOutFileOption(line);
			parseStepsOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			// new Piece(...) might throw GameError exception
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}
	/**
	 * Builds the commands necessary for the simulation: help, input, output and ticks.
	 * @return command options
	 */
	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("t").longOpt("ticks").hasArg()
				.desc("Ticks to execute the simulator's main loop (default value is " + _timeLimitDefaultValue + ").")
				.build());

		return cmdLineOptions;
	}
	/**
	 * Parser for the help option. Prints the helping guides if successful.
	 * @param line command line
	 * @param cmdLineOptions command options
	 */
	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}
	/**
	 * Parser for the input option. Sets the input file is successful. 
	 * @param line command line
	 * @param cmdLineOptions command options
	 * @throws ParseException if the file is missing
	 */
	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null) {
			throw new ParseException("An events file is missing");
		}
	}
	/**
	 * Parser for the output option. Sets the output file is successful. 
	 * @param line command line
	 * @param cmdLineOptions command options
	 * @throws ParseException if the file is missing
	 */
	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
	}
	/**
	 * Parser for the steps option. Sets the steps of the simulation if successful.
	 * @param line line
	 * @throws ParseException if the steps provided is invalid
	 */
	private static void parseStepsOption(CommandLine line) throws ParseException {
		String t = line.getOptionValue("t", _timeLimitDefaultValue.toString());
		try {
			_timeLimit = Integer.parseInt(t);
			assert (_timeLimit < 0);
		} catch (Exception e) {
			throw new ParseException("Invalid value for time limit: " + t);
		}
	}

	/**
	 * This method run the simulator on all files that ends with .ini in the given
	 * path, and compares that output to the expected output. It assumes that for
	 * example "example.ini" the expected output is stored in "example.ini.eout".
	 * The simulator's output will be stored in "example.ini.out"
	 * 
	 * @throws IOException if the file doesn't exists
	 */
	private static void test(String path) throws IOException {

		File dir = new File(System.getProperty("user.dir") + path);

		if ( !dir.exists() ) {
			throw new FileNotFoundException(path);
		}
		
		File[] files = dir.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".ini");
			}
		});

		for (File file : files) {
			test(file.getAbsolutePath(), file.getAbsolutePath() + ".out", file.getAbsolutePath() + ".eout",_timeLimitDefaultValue);
		}

	}
	/**
	 * Tests an example.
	 * @param inFile input file
	 * @param outFile output file
	 * @param expectedOutFile expected output file
	 * @param timeLimit time limit
	 * @throws IOException if the reading is unsuccessful
	 */
	private static void test(String inFile, String outFile, String expectedOutFile, int timeLimit) throws IOException {
		_outFile = outFile;
		_inFile = inFile;
		_timeLimit = timeLimit;
		startBatchMode();
		boolean equalOutput = (new Ini(_outFile)).equals(new Ini(expectedOutFile));
		System.out.println("Result for: '" + _inFile + "' : "
				+ (equalOutput ? "OK!" : ("not equal to expected output +'" + expectedOutFile + "'")));
	}

	/**
	 * Constructs a simulation controller, sets up the simulation and starts it.
	 * 
	 * @throws IOException if the files couldn't be opened
	 */
	private static void startBatchMode() throws IOException {
		Controller controller = new Controller(_inFile, _outFile,_timeLimit);
		controller.run();
	}
	/**
	 * Parses the commands and calls the startBatchMode method.
	 * @param args commands provided
	 * @throws IOException if the parse is unsuccessful
	 */
	private static void start(String[] args) throws IOException {
		parseArgs(args);
		startBatchMode();
	}
	
	/**
	 * Main method.
	 * @param args arguments
	 */
	public static void main(String[] args) {
		// Call test in order to test the simulator on all examples in a directory.
		try {
			test("/src/resources/examples/basic");
			test("/src/resources/examples/advanced");
			//test("/src/resources/examples/err"); When the events are incorrect prints the stacktrace and exits the program
			start(args);
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		}
	}
}

