package com.desaloja.app.controller;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.desaloja.app.model.Role;
import com.desaloja.app.model.User;
import com.desaloja.app.service.RoleServiceImpl;
import com.desaloja.app.service.UserServiceImpl;

/**
 * Created by geoMateoLol.
 */
@Controller
@RequestMapping("/user")
@SessionAttributes("user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private static final String LIST_TEMPLATE="/admin/user/list-user";
	private static final String ADD_EDIT_TEMPLATE="/admin/user/add-edit-user";
	private static final String LIST_REDIRECT="redirect:/user/list";

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private RoleServiceImpl roleService;
	
	@GetMapping("/add")
    public String addUser(User user, Model model){
        model.addAttribute("user", user);

        return ADD_EDIT_TEMPLATE;
    }
	
	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") long id, Model model) {
		LOGGER.info("SE ENTRA A EDITAR EL USUARIO CON ID: {}", id);
		User user = userService.findById(id);
		if(Objects.nonNull(user.getRoles())) {
			for (Role role : user.getRoles()) {
				LOGGER.info("ROL RECUPERADO :{}", role.getName());
			}
		}
		model.addAttribute("user", user);
		return ADD_EDIT_TEMPLATE;
	}
	
	@PostMapping("/save")
	public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult result,
			@RequestParam("lblIdsRoles") String lblIdsRoles, Model model,SessionStatus status) {
		LOGGER.info("IDS DE LOS ROLES: {}", lblIdsRoles);
		model.addAttribute("user", user);
		if (result.hasErrors()) {
			return ADD_EDIT_TEMPLATE;
		}

		if (Objects.nonNull(user.getId())) {
			User userBase = userService.findById(user.getId());
			userBase.setFirstName(user.getFirstName());
			userBase.setLastName(user.getLastName());
			
			userService.save(userBase);
		} else {
			userService.save(user);
		}
		status.setComplete();
		return LIST_REDIRECT + "?success";
	}
	
	/*@GetMapping("/list")
	public String listUser(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {		
		Pageable pageable = PageRequest.of(page, 4);
		Page<User> usuarios = userService.findByActivePaged(Boolean.TRUE, pageable);
		PageRender<User> pageRender = new PageRender<>("/", usuarios);

		model.addAttribute("page", pageRender);
		model.addAttribute("listUsers", usuarios);
		return LIST_TEMPLATE;
	}*/
	
	@GetMapping("/list")
	public String listUser(Model model) {			
		List<User> usuarios = userService.findAll();		
		model.addAttribute("listUsers", usuarios);
		return LIST_TEMPLATE;
	}
	
	@GetMapping(value = "/cargar-roles/{term}", produces = "application/json")
	public @ResponseBody List<Role> cargarRoles(@PathVariable(name = "term") String term) {
		LOGGER.info("SE ENTRA A BUSCAR ROLES CON EL CRITERIO: {}", term);
		return roleService.findByNombre(term);
	}

	@GetMapping("/add-rol/{idRol}")
	public String addRol(@PathVariable(name = "idRol",required = false) Long idRol,
			User user, Model model) {
		LOGGER.info("SE INGRESA A AGREGAR UN ROL CON ID: {}", idRol);
		//LOGGER.info("SE INGRESA A AGREGAR UN ROL CON ID22: {}", idUser);
		Role role = roleService.findById(idRol);
		//model.addAttribute("user", user);
		user.addRole(role);
		LOGGER.info("ROLES AGREGADOS AL USUARIO: {}", user.getRoles().size());
		LOGGER.info("DATOS DEL USUARIO: {}", user);
		String url = "redirect:".concat(ADD_EDIT_TEMPLATE).concat("/").concat(user.getId().toString());
		LOGGER.info("URL PARA EL USUARIO: {}", url);
		return url;
	}

}
