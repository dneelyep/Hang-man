FLAGS=-Xlint:cast -Xlint:classfile -Xlint:deprecation -Xlint:dep-ann -Xlint:divzero -Xlint:empty -Xlint:fallthrough -Xlint:finally -Xlint:options -Xlint:overrides -Xlint:path -Xlint:processing -Xlint:rawtypes -Xlint:serial -Xlint:static -Xlint:try -Xlint:unchecked -Xlint:varargs

SRC        = ./src/com/hang_man/game/*.java
CLASS      = ./bin/
CLASSPATH  = ~/Programming/Java/hang_man/bin
                                              #:~/.junit/junit4.10/junit-4.10.jar

all: $(SRC) $(CLASS)
        # Compile source files.
	javac -cp $(CLASSPATH) $(FLAGS) -d bin/ $(SRC)

        # Check for static analysis bugs.
	java -cp $(CLASSPATH) -jar ~/findbugs-2.0.0/lib/findbugs.jar -textui -emacs $(CLASS)

docs: # Regenerate my javadoc stuff.
	javadoc -author -classpath $(CLASSPATH) $(SRC) -d etc/docs/
