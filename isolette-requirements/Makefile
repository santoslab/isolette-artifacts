target=isolette-requirements

pdf:	clean
	pdflatex -halt-on-error $(target) >/dev/null
	-@bibtex $(target)
	pdflatex -halt-on-error $(target) >/dev/null
	pdflatex $(target) >/dev/null
	-@echo
	-@echo SUMMARY OF WARNINGS:
	-@grep -a Warning $(target).log

text:	clean
	pdflatex -halt-on-error $(target) >/dev/null
	bibtex $(target)
	pdflatex $(target) >/dev/null
	pdflatex $(target) >/dev/null
	@echo
	@echo SUMMARY OF WARNINGS:
	-@grep -a Warning $(target).log
	htlatex $(target) >/dev/null
	lynx -dump -nonumbers -nomargins $(target).html | sed 's/$$/\r/' >$(target).txt

clean:
	-@rm -f *.log *.out *.aux *.snm *.nav *.toc *.vrb *~ 
	-@rm -f $(target).lof $(target).lot $(target).blg $(target).bbl $(target).idv 
	-@rm -f *.4ct *.4tc $(target).css *.dvi 
	-@rm -f $(target).lg $(target).tmp $(target).xref zz$(target).ps
