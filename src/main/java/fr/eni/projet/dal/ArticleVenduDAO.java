package fr.eni.projet.dal;

import java.util.List;

import fr.eni.projet.bo.ArticleVendu;

public interface ArticleVenduDAO {
	public int insertArticle (ArticleVendu articleVendu);
	public ArticleVendu selectBy (ArticleVendu articleVendu);
	public List<ArticleVendu> selectAllArticles();
	public List<ArticleVendu> selectArticleByCategorie();
	
}
