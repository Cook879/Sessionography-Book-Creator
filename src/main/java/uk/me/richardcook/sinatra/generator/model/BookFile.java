package uk.me.richardcook.sinatra.generator.model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * An extension to PrintWriter, this handles all of our book writing, including producing any LaTEX we need
 */
public class BookFile extends PrintWriter {

	public static DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
	private Map<String, String> bookStrings;

	public BookFile( String fileName, Map<String, String> bookStrings ) throws FileNotFoundException {
		super( fileName );
		this.bookStrings = bookStrings;
	}

	public static String createBoldText( String text ) {
		return "\\bold{" + text + "}";
	}

	public static String createItalicText( String text ) {
		return "\\ita{" + text + "}";
	}

	public static String createLabel( String label, String text ) {
		return "\\lbl{" + removeSpecialCharacters( label ) + "}{" + removeSpecialCharacters( text ) + "}";
	}

	public static String createChapter( String title ) {
		return "\\chapter{" + removeSpecialCharacters( title ) + "}";
	}

	public static String createNewPage() {
		return "\\newpage";
	}

	public static String createSection( String title ) {
		return "\\section{" + removeSpecialCharacters( title ) + "}";
	}

	public static String createSubSection( String title ) {
		return "\\subsection{" + removeSpecialCharacters( title ) + "}";
	}

	public static String createSubSubSection( String title ) {
		return "\\subsubsection{" + removeSpecialCharacters( title ) + "}";
	}

	public static String createNewLine() {
		return "\\newline";
	}

	//http://stackoverflow.com/questions/10813154/converting-number-to-letter
	public static String getCharForNumber( int i ) {
		i--;
		int quot = i / 26;
		int rem = i % 26;
		char letter = (char) ( (int) 'a' + rem );
		if ( quot == 0 ) {
			return "" + letter;
		} else {
			return getCharForNumber( quot ) + letter;
		}
	}

	public static String createSuperScriptText( String text ) {
		return ( "\\textsuperscript{" + removeSpecialCharacters( text ) + "}" );
	}

	public static String createBeginDescription() {
		return "\\begin{description}";
	}

	public static String createEndDescription() {
		return "\\end{description}";
	}

	public static String createSetLength( String variable, String value ) {
		return "\\setlength{" + removeSpecialCharacters( variable ) + "}{" + removeSpecialCharacters( value ) + "}";
	}

	public static String createItem( String item ) {
		return "\\item " + removeSpecialCharacters( item );
	}

	public static String createFootnoteSize() {
		return "\\footnotesize";
	}

	public static String createTabJoin( String string1, String string2 ) {
		return removeSpecialCharacters( string1 ) + " & " + removeSpecialCharacters( string2 );
	}

	public static String createMultiColumn( int columns, String value ) {
		return "\\multicolumn{" + columns + "}{l}{" + removeSpecialCharacters( value ) + "}";
	}

	public static String createSuperTabular( int i ) {
		String str = "\\begin{supertabular}{ ";
		for ( int j = 0; j < i; j++ ) {
			str += "l ";
		}
		str += "}";
		return str;
	}

	public static String createLongTable( char align, int i ) {
		String str = "\\begin{longtable}[" + align + "]{ ";
		for ( int j = 0; j < i; j++ ) {
			str += "l ";
		}
		str += "}";
		return str;
	}

	public static String createTabJoin( String string1, String string2, String string3 ) {
		return string1 + " & " + string2 + " & " + string3 + " \\\\";
	}

	public static String createTabJoin( String string1, String string2, String string3, String string4 ) {
		return removeSpecialCharacters( string1 ) + " & " + removeSpecialCharacters( string2 ) + " & " +
				       removeSpecialCharacters( string3 ) + " & " + removeSpecialCharacters( string4 ) + " \\\\";
	}

	public static String createBeginFootnoteSize() {
		return "\\begin{footnotesize}";
	}

	public static String createEndFootnoteSize() {
		return "\\end{footnotesize}";
	}

	public static String createEndSuperTabular() {
		return "\\end{supertabular}";
	}

	public static String createEndLongTable() {
		return "\\end{longtable}";
	}

	public static String removeSpecialCharacters( String string ) {
		// We have 10 special characters to process...
		// Skipping backslash and curly braces as too much hassle and unlikely to be used
		// #3 - \
		//string = string.replaceAll( "\\\\", "\\textbackslash\\{\\}" );
		// #1 - {
		//string = string.replaceAll( "\\{", "\\\\{" );
		// #2 - }
		//string = string.replaceAll( "\\}", "\\\\}" );
		// #4 - #
		string = string.replaceAll( "\\#", "\\\\#" );
		// #5 - $
		string = string.replaceAll( "\\$", "\\\\$" );
		// #6 - %
		string = string.replaceAll( "\\%", "\\\\%" );
		// #7 - &
		string = string.replaceAll( "\\&", "\\\\&" );
		// #8 - ^
		string = string.replaceAll( "\\^", "\\textasciicircum" );
		// #9 - _
		string = string.replaceAll( "_", "\\_" );
		// #10 - ~
		//string = string.replaceAll( "~", "\\textasciitilde" );

		// And we want some accents
		string = string.replaceAll( "ñ", "\\\\~{n}" );
		string = string.replaceAll( "ë", "\\\\\"{e}" );
		string = string.replaceAll( "à", "\\\\`{a}" );
		return string.replaceAll( "é", "\\\\'{e}" );
	}

	public void setUpDocument( String subtitle, boolean twoCols, boolean intro ) throws Exception {
		if ( twoCols ) {
			println( "\\documentclass[twocolumn]{book}" );
		} else {
			println( "\\documentclass{book}" );
		}

		println( "\\usepackage{supertabular}" );
		println( "\\usepackage[T1]{fontenc}" );
		println( "\\usepackage{titling}" );
		println( "\\usepackage{alphalph}" );
		println( "\\usepackage{longtable}" );
		println( "\\usepackage{hyperref}" );

		getStyleRules();

		println( "\\begin{document}" );
		println( "\\pagenumbering{gobble}" );

		println( "\\title{" + removeSpecialCharacters( getBookString( "title" ) ) + "}" );
		if ( subtitle != null ) {
			println( "\\subtitle{" + removeSpecialCharacters( subtitle ) + "}" );
		}
		println( "\\date{" + getBookString( "version" ) + "}" );
		println( "\\author{" + removeSpecialCharacters( getBookString( "author" ) ) + "}" );

		println( "\\maketitle" );

		if ( twoCols )
			println( "\\onecolumn" );
		// get copyright stuff
		printCopyright();

		// Check acknowledgments
		String acknowledgements = getBookString( "acknowledgements" );
		if ( ! acknowledgements.equals( "" ) ) {
			printNewPage();
			println( "\\section*{" + getBookString( "acknowledgements-title" ) + "}" );
			println( removeSpecialCharacters( acknowledgements ) );
		}

		println( "\\tableofcontents" );

		if ( twoCols )
			println( "\\twocolumn" );

		println( "\\pagenumbering{arabic}" );

		if( intro ) {
			printChapter( getBookString( "introduction-title" ) );
			println( removeSpecialCharacters( getBookString( "introduction" ) ) );
		}
	}

	private void printCopyright() {
		println( "Book contents \\copyright \\the\\year \\ " + removeSpecialCharacters( getBookString( "author" ) ) + ". All rights reserved." );
		printNewLine();
		println( getBookString( "book-copyright" ) );

		printNewLine();
		printNewLine();

		println( "Book structure and layout \\copyright \\the\\year \\ Richard Cook." );
		println( "The software used to create this book is released under the Creative Commons Attribution-ShareAlike 4.0 International license. " +
				         "This means that you can use and adapt the software however you like, even commercially, as long as you attribute the original, " +
				         " indicating what changes were made, and share it under the Creative Commons Attribution-ShareAlike license as well." );

		printNewLine();
		printNewLine();

		println( "You can obtain the software to make your own book from " );
		println( "\\url{http://www.richardcook.me.uk/sessionography}" );
		println( "Using this software to make your own book? Let me know at \\href{mailto:sessionography@richardcook.me.uk}{ \\nolinkurl{sessionography@richardcook.me.uk} }" );
		println( "Need help, found some bugs or have feature requests? Email \\href{mailto:sessionography@richardcook.me.uk}{ \\nolinkurl{sessionography@richardcook.me.uk} }" );

		printNewLine();
		printNewLine();

		println( removeSpecialCharacters( getBookString( "book-errors" ) ) );
	}

	public void getStyleRules() throws Exception {
		println( "\\renewcommand{\\thesubsection}{\\alph{subsection}}" );
		println( "\\newcommand{\\lbl}[2]{{\\textit{#1}} #2}" );
		println( "\\newcommand{\\ita}[1]{{\\textit{#1}}}" );
		println( "\\newcommand{\\bold}[1]{{\\textbf{#1}}}" );
		println( "\\newcommand\\tab[1][1cm]{\\hspace*{#1}}" );

		println( "\\newcommand{\\subtitle}[1]{%" );
		println( "\\posttitle{%" );
		println( "\\par\\end{center}" );
		println( "\\begin{center}\\large#1\\end{center}" );
		println( "\\vskip0.5em}%" );
		println( "}" );

		println( "\\renewcommand\\thesubsection{\\mbox{\\alphalph{\\value{subsection}}}}" );
	}

	public void endDocument() throws Exception {
		println( "\\end{document}" );
	}

	public void closeFile() throws Exception {
		flush();
		close();
	}

	public void printBoldText( String text ) {
		println( createBoldText( text ) );
	}

	public void printItalicText( String text ) {
		println( createItalicText( text ) );
	}

	public void printLabel( String label, String text ) {
		println( createLabel( label, text ) );
	}

	public void printChapter( String title ) {
		println( createChapter( title ) );
	}

	public void printNewPage() {
		println( createNewPage() );
	}

	public void printSection( String title ) {
		println( createSection( title ) );
	}

	public void printSubSection( String title ) {
		println( createSubSection( title ) );
	}

	public void printSubSubSection( String title ) {
		println( createSubSubSection( title ) );
	}

	public void printNewLine() {
		println( createNewLine() );
	}

	public void printSuperScriptText( String text ) {
		println( createSuperScriptText( text ) );
	}

	public void printBeginDescription() {
		println( createBeginDescription() );
	}

	public void printEndDescription() {
		println( createEndDescription() );
	}

	public void printSetLength( String variable, String value ) {
		println( createSetLength( variable, value ) );
	}

	public void printItem( String item ) {
		println( createItem( item ) );
	}

	public void printFootnoteSize() {
		println( createFootnoteSize() );
	}

	public void printTabJoin( String string1, String string2 ) {
		println( createTabJoin( string1, string2 ) );
	}

	public void printMultiColumn( int columns, String value ) {
		println( createMultiColumn( columns, value ) + " \\\\" );
	}

	public void printSuperTabular( int i ) {
		println( createSuperTabular( i ) );
	}

	public void printLongTable( char align, int i ) {
		println( createLongTable( align, i ) );
	}

	public void printTabJoin( String string1, String string2, String string3 ) {
		println( createTabJoin( string1, string2, string3 ) );
	}

	public void printTabJoin( String string1, String string2, String string3, String string4 ) {
		println( createTabJoin( string1, string2, string3, string4 ) );
	}

	public void printBeginFootnoteSize() {
		println( createBeginFootnoteSize() );
	}

	public void printEndFootnoteSize() {
		println( createEndFootnoteSize() );
	}

	public void printEndSuperTabular() {
		println( createEndSuperTabular() );
	}

	public void printEndLongTable() {
		println( createEndLongTable() );
	}

	public String getBookString( String name ) {
		return bookStrings.get( name );
	}

}
