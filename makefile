JCC=javac

all:
	$(JCC) ComplexConversionException.java
	$(JCC) Complex.java
	$(JCC) ComplexMath.java
	$(JCC) Fractal.java
	$(JCC) FractalViewer.java

clean:
	rm -f *.class