tcc2 = Repositório para o desenvolvimento do TCC 2'
====

LaTex para sublime text

https://github.com/SublimeText/LaTeXTools#readme

On Windows, both MikTeX and TeXlive are supported, but TeXLive support is currently better. Also, you must be running a current (>=1.4) version of the Sumatra PDF previewer. Install these as usual; then, add the SumatraPDF directory to your PATH (this requirement will be removed at some point).

You now need to set up inverse search in Sumatra PDF. However, the GUI for doing this is hidden in Sumatra until you open a PDF file that has actual synchronization information (that is, an associated .synctex.gz file): see here. If you have one such file, then open it, go to Settings|Options, and enter "C:\Program Files\Sublime Text 2\sublime_text.exe" "%f:%l" for ST2, and "C:\Program Files\Sublime Text 3\sublime_text.exe" "%f:%l" for ST3, as the inverse-search command line (in the text-entry field at the bottom of the options dialog). If you don't already have a file with sync information, you can easily create one: compile any LaTex file you already have (or create a new one) with pdflatex -synctex=1 <file.tex>, and then open the resulting PDF file in SumatraPDF.

As an alternative, you can open a command-line console (run cmd.exe), and issue the following command:

sumatrapdf.exe -inverse-search "\"C:\Program Files\Sublime Text 2\sublime_text.exe\" \"%f:%l\""
(this assumes that sumatraPDF is in your path; replace 2 with 3 for ST3 of course). I'm sorry this is not straightforward---it's not my fault :-)

Recent versions of MikTeX add themselves to your path automatically, but in case the build system does not work, that's the first thing to check. TeXlive can also add itself to your path.

Finally, you must check the file LaTeX.sublime-build in the directory in which you unzipped the LaTeXTools plugin to make sure that the configuration reflects your preferred TeX distribution. Open the file and scroll down to the section beginning with the keyword "windows". You will see that there are two blocks of settings for the "cmd" and "path" keywords; by default, the MikTeX one is active, and the TeXlive one is commented out. If you use MikTeX, you don't need to change anything: congratulations, you are done!

If instead you use TeXlive, comment out the lines between the comments *** BEGIN MikTeX 2009 *** and *** END MikTeX 2009 ***, and uncomment the lines between the comments *** BEGIN TeXlive 2011 *** and *** END TeXlive 2011 ***. Do not uncomment the BEGIN/END lines themselves---just the lines between them. Now you are really done! (The dates "2009" and "2011" are only indicative.)

TeXlive has one main advantage over MikTeX: it supports file names and paths with spaces. Furthermore, it is easier to change the compilation engine from the default, pdflatex, to e.g. xelatex: see below for details.

''