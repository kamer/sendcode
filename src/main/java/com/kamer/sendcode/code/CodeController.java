package com.kamer.sendcode.code;

import com.kamer.sendcode.entity.Code;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created on December, 2019
 *
 * @author kamer
 */
@Controller
@AllArgsConstructor
public class CodeController {

	private final CodeService codeService;

	@GetMapping("/")
	public String index() {

		return "index";
	}

	@GetMapping("/{path}")
	public String getOrSetCode(@PathVariable("path") String path, Model model) {

		final Optional<Code> codeOptional = codeService.getCodeWithPath(path);

		if (codeOptional.isPresent()) {

			codeService.deleteCode(path);

			final Code code = codeOptional.get();

			final String languageName = LANGUAGES_MAP.get(code.getLanguage());

			model.addAttribute("code", code);

			return "show_code";
		}

		model.addAttribute("languages", LANGUAGES_MAP);

		model.addAttribute("path", path);

		model.addAttribute("code", new Code());

		return "new_code";
	}

	@PostMapping("/{path}")
	public String saveNewCode(RedirectAttributes redirectAttributes, Code code) {

		codeService.createNewCode(code);

		redirectAttributes.addFlashAttribute("path", code.getPath());

		return "redirect:/success";
	}

	@GetMapping("/success")
	public String successPage(Model model) {

		if (model.containsAttribute("path")) {

			return "success";
		}
		else {

			return "redirect:/";
		}
	}

	//@formatter:off
	public final static Map<String, String> LANGUAGES_MAP =
			Stream.of(new Object[][]{
					{ "markup", "Markup" },
					{ "css", "CSS" },
					{ "clike", "C-like" },
					{ "js", "JavaScript" },
					{ "abap", "ABAP" },
					{ "actionscript", "ActionScript" },
					{ "ada", "Ada" },
					{ "apacheconf", "Apache Configuration" },
					{ "applescript", "AppleScript" },
					{ "arduino", "Arduino" },
					{ "asciidoc", "AsciiDoc" },
					{ "asm6502", "6502 Assembly" },
					{ "aspnet", "ASP.NET (C#)" },
					{ "autohotkey", "AutoHotkey" },
					{ "autoit", "AutoIt" },
					{ "bash", "Bash" },
					{ "basic", "BASIC" },
					{ "batch", "Batch" },
					{ "brainfuck", "Brainfuck" },
					{ "c", "C" },
					{ "csharp", "C#" },
					{ "cpp", "C++" },
					{ "coffeescript", "CoffeeScript" },
					{ "cmake", "CMake" },
					{ "clojure", "Clojure" },
					{ "crystal", "Crystal" },
					{ "csp", "Content-Security-Policy" },
					{ "css-extras", "CSS Extras" },
					{ "d", "D" },
					{ "dart", "Dart" },
					{ "diff", "Diff" },
					{ "django", "Django/Jinja2" },
					{ "dns-zone", "DNS zone file" },
					{ "dockerfile", "Docker" },
					{ "eiffel", "Eiffel" },
					{ "ejs", "EJS" },
					{ "elixir", "Elixir" },
					{ "elm", "Elm" },
					{ "erlang", "Erlang" },
					{ "fsharp", "F#" },
					{ "firestore-security-rules", "Firestore security rules" },
					{ "flow", "Flow" },
					{ "fortran", "Fortran" },
					{ "ftl", "FreeMarker Template Language" },
					{ "gcode", "G-code" },
					{ "gdscript", "GDScript" },
					{ "gherkin", "Gherkin" },
					{ "git", "Git" },
					{ "glsl", "GLSL" },
					{ "gml", "GameMaker Language" },
					{ "go", "Go" },
					{ "graphql", "GraphQL" },
					{ "groovy", "Groovy" },
					{ "haml", "Haml" },
					{ "haskell", "Haskell" },
					{ "hcl", "HCL" },
					{ "http", "HTTP" },
					{ "j", "J" },
					{ "java", "Java" },
					{ "javadoc", "JavaDoc" },
					{ "javadoclike", "JavaDoc-like" },
					{ "javastacktrace", "Java stack trace" },
					{ "jolie", "Jolie" },
					{ "JSDoc - jsdoc" },
					{ "JS Extras - js-extras" },
					{ "JS Templates", "js-templates" },
					{ "JSON", "json" },
					{ "JSONP", "jsonp" },
					{ "JSON5", "json5" },
					{ "Julia", "julia" },
					{ "Kotlin", "kotlin" },
					{ "LaTeX", "latex" },
					{ "Less", "less" },
					{ "Liquid", "liquid" },
					{ "Lisp", "lisp" },
					{ "LiveScript", "livescript" },
					{ "Makefile", "makefile" },
					{ "Markdown", "markdown" },
					{ "MATLAB", "matlab" },
					{ "NASM", "nasm" },
					{ "nginx", "nginx" },
					{ "Objective-C", "objectivec" },
					{ "Pascal", "pascal" },
					{ "Perl", "perl" },
					{ "PHP", "php" },
					{ "PHPDoc", "phpdoc" },
					{ "PHP Extras", "php-extras" },
					{ "PL/SQL", "plsql" },
					{ "PowerShell", "powershell" },
					{ ".properties", "properties" },
					{ "Python", "python" },
					{ "Q (kdb+ database)", "q" },
					{ "QML", "qml" },
					{ "R", "r" },
					{ "React JSX", "jsx" },
					{ "React TSX", "tsx" },
					{ "Regex", "regex" },
					{ "reST (reStructuredText)", "rest" },
					{ "Ruby", "ruby" },
					{ "Rust", "rust" },
					{ "Sass (Sass)", "sass" },
					{ "Sass (Scss)", "scss" },
					{ "Scala", "scala" },
					{ "Solidity (Ethereum)", "solidity" },
					{ "SPARQL", "sparql" },
					{ "SQL", "sql" },
					{ "Swift", "swift" },
					{ "TypeScript", "typescript" },
					{ "Verilog", "verilog" },
					{ "VHDL", "vhdl" },
					{ "vim", "vim" },
					{ "Visual Basic", "visual-basic" },
					{ "WebAssembly", "wasm" },
					{ "Wiki markup", "wiki" },
					{ "YAML", "yml" }
			}).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
	//@formatter:on

}
