package com.perfree.controller.front;

import com.perfree.controller.BaseController;
import com.perfree.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/articleList/{pageIndex}")
    public String articleListPage(@PathVariable("pageIndex") int pageIndex) {
        return currentThemePage() + "/articleList";
    }

    @RequestMapping("/article/{articleId}")
    public String articlePage(@PathVariable("articleId") String articleId, Model model) {
        model.addAttribute("article", articleService.getById(articleId));
        return currentThemePage() + "/article";
    }
}