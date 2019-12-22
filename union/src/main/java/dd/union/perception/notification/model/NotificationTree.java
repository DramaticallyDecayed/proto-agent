package dd.union.perception.notification.model;


import dd.union.perception.notification.model.types.*;

/**
 * Представление уведомления в фоме дерева абстрактных типов (для поля CONTENT). Свойства непосредственно уведомления представелны конкретными
 * полями
 * <p/>
 * Created by slebedev on 18.07.2017.
 */
public class NotificationTree {
    private String treaty;
    private Integer format;

    private IdentNumberType identNumber;
    private AbstractAtomType references;
    private StringType remarks;

    private AbstractTypeList content;

    private IdentNumberType end;

    public IdentNumberType getIdentNumber() {
        return identNumber;
    }

    public void setIdentNumber(IdentNumberType identNumber) {
        this.identNumber = identNumber;
    }

    public AbstractAtomType getReferences() {
        return references;
    }

    public void setReferences(AbstractAtomType references) {
        this.references = references;
    }

    public StringType getRemarks() {
        return remarks;
    }

    public void setRemarks(StringType remarks) {
        this.remarks = remarks;
    }

    public IdentNumberType getEnd() {
        return end;
    }

    public void setEnd(IdentNumberType end) {
        this.end = end;
    }

    public String getTreaty() {
        if (treaty != null) {
            return treaty;
        } else {
            return getIdentNumber().getTreaty();
        }
    }

    public void setTreaty(String treaty) {
        this.treaty = treaty;
    }

    public Integer getFormat() {
        if (format != null) {
            return format;
        } else {
            return getIdentNumber().getFormat();
        }
    }

    public void setFormat(Integer format) {
        this.format = format;
    }

    public AbstractTypeList<IType> getContent() {
        return content;
    }

    public void setContent(AbstractTypeList content) {
        this.content = content;
    }

    public AbstractTypeList<IType> getAll() {
        return getContent().buildIn(
                new AbstractTypeList()
                        .build(getIdentNumber())
                        .build(getReferences())
                        .build(getRemarks())
                        .build(getEnd())
        );
    }
}
