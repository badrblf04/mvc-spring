package ma.enset.mvcspring.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import ma.enset.mvcspring.entities.Product;
import ma.enset.mvcspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductController {


    private ProductRepository productRepository;

    @GetMapping("/")
    public String home() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "size", defaultValue = "3") int s,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<Product> products = productRepository
                .findByNameContainsIgnoreCase(keyword, PageRequest.of(p, s));

        model.addAttribute("listProducts", products.getContent());
        model.addAttribute("page", new int[products.getTotalPages()]);
        model.addAttribute("currentPage", p);
        model.addAttribute("keyword", keyword); // Pour garder la valeur du champ
        return "products";
    }



    @GetMapping("/delete")
    public String delete(@RequestParam(name = "id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/index";
    }
}
