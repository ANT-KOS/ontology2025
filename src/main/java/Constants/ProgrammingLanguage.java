package Constants;

public enum ProgrammingLanguage {
    ADA("Ada"),
    APEX("Apex"),
    ASSEMBLY("Assembly"),
    BASH_SHELL("Bash/Shell (all shells)"),
    C("C"),
    C_SHARP("C#"),
    C_PLUS_PLUS("C++"),
    CLOJURE("Clojure"),
    COBOL("Cobol"),
    CRYSTAL("Crystal"),
    DART("Dart"),
    DELPHI("Delphi"),
    ELIXIR("Elixir"),
    ERLANG("Erlang"),
    F_SHARP("F#"),
    FORTRAN("Fortran"),
    GDSCRIPT("GDScript"),
    GO("Go"),
    GROOVY("Groovy"),
    HASKELL("Haskell"),
    HTML_CSS("HTML/CSS"),
    JAVA("Java"),
    JAVASCRIPT("JavaScript"),
    JULIA("Julia"),
    KOTLIN("Kotlin"),
    LISP("Lisp"),
    LUA("Lua"),
    MATLAB("MATLAB"),
    MICROPYTHON("MicroPython"),
    NIM("Nim"),
    OBJECTIVE_C("Objective-C"),
    OCAML("OCaml"),
    PERL("Perl"),
    PHP("PHP"),
    POWERSHELL("PowerShell"),
    PROLOG("Prolog"),
    PYTHON("Python"),
    R("R"),
    RUBY("Ruby"),
    RUST("Rust"),
    SCALA("Scala"),
    SOLIDITY("Solidity"),
    SQL("SQL"),
    SWIFT("Swift"),
    TYPESCRIPT("TypeScript"),
    VBA("VBA"),
    VISUAL_BASIC_NET("Visual Basic (.Net)"),
    ZEPHYR("Zephyr"),
    ZIG("Zig");

    private final String languageName;

    ProgrammingLanguage(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageName() {
        return languageName;
    }
}
