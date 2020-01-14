package com.kamer.sendcode.code;

import com.kamer.sendcode.entity.Code;
import com.kamer.sendcode.entity.Languages;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

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

		if(codeOptional.isPresent()){

			codeService.deleteCode(path);

			final Code code = codeOptional.get();

			final String languageName = Languages.languagesMap.get(code.getLanguage());

			model.addAttribute("code", code);

			return "show_code";
		}

		model.addAttribute("languages", Languages.languagesMap);

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
	public String successPage(Model model){

		if(model.containsAttribute("path")){

			return "success";
		} else {

			return "redirect:/";
		}
	}
}
