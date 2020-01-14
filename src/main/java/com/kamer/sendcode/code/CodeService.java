package com.kamer.sendcode.code;

import com.kamer.sendcode.entity.Code;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created on December, 2019
 *
 * @author kamer
 */
@Service
@AllArgsConstructor
public class CodeService {

	private final CodeRepository codeRepository;

	/**
	 * Removes related code with given path.
	 */
	void deleteCode(String path) {

		if (isPathExist(path)) {
			codeRepository.deleteById(path);
		}
	}

	/**
	 * Saves given Code object.
	 * Database should be checked before with isPathExist() method.
	 */
	void createNewCode(Code code) {

		codeRepository.save(code);
	}

	/**
	 * Gets code object with given path.
	 */
	Optional<Code> getCodeWithPath(String path) {

		return codeRepository.findById(path);
	}


	/**
	 * Checks database with given path.
	 */
	private boolean isPathExist(String path) {

		return codeRepository.existsById(path);
	}

}
