package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping("/find")
    public String find(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null)
            owner.setLastName("");

        var owners = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if(owners.isEmpty()) {
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        if(owners.size() == 1) {
            owner = owners.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        model.addAttribute("owners", owners);
        return "owners/ownersList";

    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));
        return mav;
    }

    @GetMapping("/new")
    public String initOwnerCreationForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/new")
    public String processCreationForm(Owner owner, BindingResult result) {
        if(result.hasErrors())
            return "owners/createOrUpdateOwnerForm";
        var savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @GetMapping("/{ownersId}/edit")
    public String initUpdateOwnerForm(Model model, @PathVariable Long ownersId) {
        model.addAttribute("owner", ownerService.findById(ownersId));
        return "owners/createOrUpdateOwnerForm";
    }

    @PostMapping("/{ownersId}/edit")
    public String processUpdateOwnerForum(Owner owner, BindingResult result, @PathVariable Long ownersId) {
        if (result.hasErrors())
            return "owners/createOrUpdateOwnerForm";
        owner.setId(ownersId);
        var savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }
}
