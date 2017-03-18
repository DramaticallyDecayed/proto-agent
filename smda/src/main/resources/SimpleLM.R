getCorrelation <- function(numericalColumn, categoricalColumn){
    mat <- matrix(c(numericalColumn, categoricalColumn),nrow=length(numericalColumn))
    mat <- na.omit(mat)
    numericalColumn <- mat[,1]
    categoricalColumn <- mat[,2]
    model <- lm(numericalColumn ~ 0 + categoricalColumn)
    res <- broom::tidy(model)
    res <- res[order(res[,5]),]
    res <- subset(res, p.value < 0.005)$term
    return(res)
}

