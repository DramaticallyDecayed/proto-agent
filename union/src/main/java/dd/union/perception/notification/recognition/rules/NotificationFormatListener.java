// Generated from D:/code/IdeaProjects/ProtoAgent/union/src/main/resources/antlr_grammar_rules\NotificationFormat.g4 by ANTLR 4.7
package dd.union.perception.notification.recognition.rules;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link NotificationFormatParser}.
 */
public interface NotificationFormatListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link NotificationFormatParser#notification}.
	 * @param ctx the parse tree
	 */
	void enterNotification(NotificationFormatParser.NotificationContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotificationFormatParser#notification}.
	 * @param ctx the parse tree
	 */
	void exitNotification(NotificationFormatParser.NotificationContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotificationFormatParser#ident_item}.
	 * @param ctx the parse tree
	 */
	void enterIdent_item(NotificationFormatParser.Ident_itemContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotificationFormatParser#ident_item}.
	 * @param ctx the parse tree
	 */
	void exitIdent_item(NotificationFormatParser.Ident_itemContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotificationFormatParser#bullet}.
	 * @param ctx the parse tree
	 */
	void enterBullet(NotificationFormatParser.BulletContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotificationFormatParser#bullet}.
	 * @param ctx the parse tree
	 */
	void exitBullet(NotificationFormatParser.BulletContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotificationFormatParser#deployed_bullet}.
	 * @param ctx the parse tree
	 */
	void enterDeployed_bullet(NotificationFormatParser.Deployed_bulletContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotificationFormatParser#deployed_bullet}.
	 * @param ctx the parse tree
	 */
	void exitDeployed_bullet(NotificationFormatParser.Deployed_bulletContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotificationFormatParser#non_deployed_bullet}.
	 * @param ctx the parse tree
	 */
	void enterNon_deployed_bullet(NotificationFormatParser.Non_deployed_bulletContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotificationFormatParser#non_deployed_bullet}.
	 * @param ctx the parse tree
	 */
	void exitNon_deployed_bullet(NotificationFormatParser.Non_deployed_bulletContext ctx);
	/**
	 * Enter a parse tree produced by {@link NotificationFormatParser#text}.
	 * @param ctx the parse tree
	 */
	void enterText(NotificationFormatParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by {@link NotificationFormatParser#text}.
	 * @param ctx the parse tree
	 */
	void exitText(NotificationFormatParser.TextContext ctx);
}