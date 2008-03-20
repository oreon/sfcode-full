
import java.io.Serializable;
import java.math.BigDecimal;

public class BuyNowPostData
    implements Serializable
{

    public BuyNowPostData()
    {
        itemNumber = null;
        undefinedQuantity = null;
        firstOptionFieldName = null;
        secondOptionFieldName = null;
        continueLabel = null;
        noteFieldLabel = null;
        paymentPageHeaderImage = null;
        headerBgColor = null;
        headerBorderColor = null;
        headerPayFlowColor = null;
        paymentPageBgColor = null;
        promptPaymentNote = null;
        promptShippingAddress = null;
        pageStyle = null;
        submissionMethod = null;
        currencyCode = null;
        handlingCost = null;
        invoiceNumber = null;
        shippingCost = null;
        additionalItemShippingCost = null;
        tax = null;
        firstOptionFieldValue = null;
        secondOptionFieldValue = null;
    }

    public String getItemNumber()
    {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber)
    {
        this.itemNumber = itemNumber;
    }

    public String getUndefinedQuantity()
    {
        return undefinedQuantity;
    }

    public void setUndefinedQuantity(String undefinedQuantity)
    {
        this.undefinedQuantity = undefinedQuantity;
    }

    public String getFirstOptionFieldName()
    {
        return firstOptionFieldName;
    }

    public void setFirstOptionFieldName(String firstOptionFieldName)
    {
        this.firstOptionFieldName = firstOptionFieldName;
    }

    public String getSecondOptionFieldName()
    {
        return secondOptionFieldName;
    }

    public void setSecondOptionFieldName(String secondOptionFieldName)
    {
        this.secondOptionFieldName = secondOptionFieldName;
    }

    public String getFirstOptionFieldValue()
    {
        return firstOptionFieldValue;
    }

    public void setFirstOptionFieldValue(String firstOptionFieldValue)
    {
        this.firstOptionFieldValue = firstOptionFieldValue;
    }

    public String getSecondOptionFieldValue()
    {
        return secondOptionFieldValue;
    }

    public void setSecondOptionFieldValue(String secondOptionFieldValue)
    {
        this.secondOptionFieldValue = secondOptionFieldValue;
    }

    public String getContinueLabel()
    {
        return continueLabel;
    }

    public void setContinueLabel(String continueLabel)
    {
        this.continueLabel = continueLabel;
    }

    public String getNoteFieldLabel()
    {
        return noteFieldLabel;
    }

    public void setNoteFieldLabel(String noteFieldLabel)
    {
        this.noteFieldLabel = noteFieldLabel;
    }

    public String getPaymentPageHeaderImage()
    {
        return paymentPageHeaderImage;
    }

    public void setPaymentPageHeaderImage(String paymentPageHeaderImage)
    {
        this.paymentPageHeaderImage = paymentPageHeaderImage;
    }

    public String getHeaderBgColor()
    {
        return headerBgColor;
    }

    public void setHeaderBgColor(String headerBgColor)
    {
        this.headerBgColor = headerBgColor;
    }

    public String getHeaderBorderColor()
    {
        return headerBorderColor;
    }

    public void setHeaderBorderColor(String headerBorderColor)
    {
        this.headerBorderColor = headerBorderColor;
    }

    public String getHeaderPayFlowColor()
    {
        return headerPayFlowColor;
    }

    public void setHeaderPayFlowColor(String headerPayFlowColor)
    {
        this.headerPayFlowColor = headerPayFlowColor;
    }

    public String getPaymentPageBgColor()
    {
        return paymentPageBgColor;
    }

    public void setPaymentPageBgColor(String paymentPageBgColor)
    {
        this.paymentPageBgColor = paymentPageBgColor;
    }

    public String getPromptPaymentNote()
    {
        return promptPaymentNote;
    }

    public void setPromptPaymentNote(String promptPaymentNote)
    {
        this.promptPaymentNote = promptPaymentNote;
    }

    public String getPromptShippingAddress()
    {
        return promptShippingAddress;
    }

    public void setPromptShippingAddress(String promptShippingAddress)
    {
        this.promptShippingAddress = promptShippingAddress;
    }

    public String getPageStyle()
    {
        return pageStyle;
    }

    public void setPageStyle(String pageStyle)
    {
        this.pageStyle = pageStyle;
    }

    public String getSubmissionMethod()
    {
        return submissionMethod;
    }

    public void setSubmissionMethod(String submissionMethod)
    {
        this.submissionMethod = submissionMethod;
    }

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getHandlingCost()
    {
        return handlingCost;
    }

    public void setHandlingCost(BigDecimal handlingCost)
    {
        this.handlingCost = handlingCost;
    }

    public String getInvoiceNumber()
    {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
    }

    public BigDecimal getShippingCost()
    {
        return shippingCost;
    }

    public void setShippingCost(BigDecimal shippingCost)
    {
        this.shippingCost = shippingCost;
    }

    public BigDecimal getAdditionalItemShippingCost()
    {
        return additionalItemShippingCost;
    }

    public void setAdditionalItemShippingCost(BigDecimal additionalItemShippingCost)
    {
        this.additionalItemShippingCost = additionalItemShippingCost;
    }

    public BigDecimal getTax()
    {
        return tax;
    }

    public void setTax(BigDecimal tax)
    {
        this.tax = tax;
    }

    private String itemNumber;
    private String undefinedQuantity;
    private String firstOptionFieldName;
    private String secondOptionFieldName;
    private String continueLabel;
    private String noteFieldLabel;
    private String paymentPageHeaderImage;
    private String headerBgColor;
    private String headerBorderColor;
    private String headerPayFlowColor;
    private String paymentPageBgColor;
    private String promptPaymentNote;
    private String promptShippingAddress;
    private String pageStyle;
    private String submissionMethod;
    private String currencyCode;
    private BigDecimal handlingCost;
    private String invoiceNumber;
    private BigDecimal shippingCost;
    private BigDecimal additionalItemShippingCost;
    private BigDecimal tax;
    private String firstOptionFieldValue;
    private String secondOptionFieldValue;
}
