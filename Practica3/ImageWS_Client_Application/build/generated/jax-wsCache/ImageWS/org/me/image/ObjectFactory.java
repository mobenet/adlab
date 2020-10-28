
package org.me.image;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.me.image package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteImageResponse_QNAME = new QName("http://image.me.org/", "DeleteImageResponse");
    private final static QName _RegistrerImage_QNAME = new QName("http://image.me.org/", "RegistrerImage");
    private final static QName _RegistrerImageResponse_QNAME = new QName("http://image.me.org/", "RegistrerImageResponse");
    private final static QName _SearchbyCreaDate_QNAME = new QName("http://image.me.org/", "SearchbyCreaDate");
    private final static QName _ListImages_QNAME = new QName("http://image.me.org/", "ListImages");
    private final static QName _DeleteImage_QNAME = new QName("http://image.me.org/", "DeleteImage");
    private final static QName _ListImagesResponse_QNAME = new QName("http://image.me.org/", "ListImagesResponse");
    private final static QName _SearchbyAuthor_QNAME = new QName("http://image.me.org/", "SearchbyAuthor");
    private final static QName _ModifyImage_QNAME = new QName("http://image.me.org/", "ModifyImage");
    private final static QName _SearchbyKeywordsResponse_QNAME = new QName("http://image.me.org/", "SearchbyKeywordsResponse");
    private final static QName _SearchbyCreaDateResponse_QNAME = new QName("http://image.me.org/", "SearchbyCreaDateResponse");
    private final static QName _SearchbyTitleResponse_QNAME = new QName("http://image.me.org/", "SearchbyTitleResponse");
    private final static QName _SearchbyAuthorResponse_QNAME = new QName("http://image.me.org/", "SearchbyAuthorResponse");
    private final static QName _SearchbyKeywords_QNAME = new QName("http://image.me.org/", "SearchbyKeywords");
    private final static QName _SearchbyTitle_QNAME = new QName("http://image.me.org/", "SearchbyTitle");
    private final static QName _ModifyImageResponse_QNAME = new QName("http://image.me.org/", "ModifyImageResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.me.image
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteImage }
     * 
     */
    public DeleteImage createDeleteImage() {
        return new DeleteImage();
    }

    /**
     * Create an instance of {@link ListImagesResponse }
     * 
     */
    public ListImagesResponse createListImagesResponse() {
        return new ListImagesResponse();
    }

    /**
     * Create an instance of {@link SearchbyAuthor }
     * 
     */
    public SearchbyAuthor createSearchbyAuthor() {
        return new SearchbyAuthor();
    }

    /**
     * Create an instance of {@link ModifyImage }
     * 
     */
    public ModifyImage createModifyImage() {
        return new ModifyImage();
    }

    /**
     * Create an instance of {@link DeleteImageResponse }
     * 
     */
    public DeleteImageResponse createDeleteImageResponse() {
        return new DeleteImageResponse();
    }

    /**
     * Create an instance of {@link RegistrerImage }
     * 
     */
    public RegistrerImage createRegistrerImage() {
        return new RegistrerImage();
    }

    /**
     * Create an instance of {@link RegistrerImageResponse }
     * 
     */
    public RegistrerImageResponse createRegistrerImageResponse() {
        return new RegistrerImageResponse();
    }

    /**
     * Create an instance of {@link SearchbyCreaDate }
     * 
     */
    public SearchbyCreaDate createSearchbyCreaDate() {
        return new SearchbyCreaDate();
    }

    /**
     * Create an instance of {@link ListImages }
     * 
     */
    public ListImages createListImages() {
        return new ListImages();
    }

    /**
     * Create an instance of {@link SearchbyAuthorResponse }
     * 
     */
    public SearchbyAuthorResponse createSearchbyAuthorResponse() {
        return new SearchbyAuthorResponse();
    }

    /**
     * Create an instance of {@link SearchbyKeywords }
     * 
     */
    public SearchbyKeywords createSearchbyKeywords() {
        return new SearchbyKeywords();
    }

    /**
     * Create an instance of {@link SearchbyTitle }
     * 
     */
    public SearchbyTitle createSearchbyTitle() {
        return new SearchbyTitle();
    }

    /**
     * Create an instance of {@link ModifyImageResponse }
     * 
     */
    public ModifyImageResponse createModifyImageResponse() {
        return new ModifyImageResponse();
    }

    /**
     * Create an instance of {@link SearchbyKeywordsResponse }
     * 
     */
    public SearchbyKeywordsResponse createSearchbyKeywordsResponse() {
        return new SearchbyKeywordsResponse();
    }

    /**
     * Create an instance of {@link SearchbyCreaDateResponse }
     * 
     */
    public SearchbyCreaDateResponse createSearchbyCreaDateResponse() {
        return new SearchbyCreaDateResponse();
    }

    /**
     * Create an instance of {@link SearchbyTitleResponse }
     * 
     */
    public SearchbyTitleResponse createSearchbyTitleResponse() {
        return new SearchbyTitleResponse();
    }

    /**
     * Create an instance of {@link Image }
     * 
     */
    public Image createImage() {
        return new Image();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "DeleteImageResponse")
    public JAXBElement<DeleteImageResponse> createDeleteImageResponse(DeleteImageResponse value) {
        return new JAXBElement<DeleteImageResponse>(_DeleteImageResponse_QNAME, DeleteImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrerImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "RegistrerImage")
    public JAXBElement<RegistrerImage> createRegistrerImage(RegistrerImage value) {
        return new JAXBElement<RegistrerImage>(_RegistrerImage_QNAME, RegistrerImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrerImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "RegistrerImageResponse")
    public JAXBElement<RegistrerImageResponse> createRegistrerImageResponse(RegistrerImageResponse value) {
        return new JAXBElement<RegistrerImageResponse>(_RegistrerImageResponse_QNAME, RegistrerImageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreaDate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "SearchbyCreaDate")
    public JAXBElement<SearchbyCreaDate> createSearchbyCreaDate(SearchbyCreaDate value) {
        return new JAXBElement<SearchbyCreaDate>(_SearchbyCreaDate_QNAME, SearchbyCreaDate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImages }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "ListImages")
    public JAXBElement<ListImages> createListImages(ListImages value) {
        return new JAXBElement<ListImages>(_ListImages_QNAME, ListImages.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "DeleteImage")
    public JAXBElement<DeleteImage> createDeleteImage(DeleteImage value) {
        return new JAXBElement<DeleteImage>(_DeleteImage_QNAME, DeleteImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListImagesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "ListImagesResponse")
    public JAXBElement<ListImagesResponse> createListImagesResponse(ListImagesResponse value) {
        return new JAXBElement<ListImagesResponse>(_ListImagesResponse_QNAME, ListImagesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "SearchbyAuthor")
    public JAXBElement<SearchbyAuthor> createSearchbyAuthor(SearchbyAuthor value) {
        return new JAXBElement<SearchbyAuthor>(_SearchbyAuthor_QNAME, SearchbyAuthor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "ModifyImage")
    public JAXBElement<ModifyImage> createModifyImage(ModifyImage value) {
        return new JAXBElement<ModifyImage>(_ModifyImage_QNAME, ModifyImage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywordsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "SearchbyKeywordsResponse")
    public JAXBElement<SearchbyKeywordsResponse> createSearchbyKeywordsResponse(SearchbyKeywordsResponse value) {
        return new JAXBElement<SearchbyKeywordsResponse>(_SearchbyKeywordsResponse_QNAME, SearchbyKeywordsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyCreaDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "SearchbyCreaDateResponse")
    public JAXBElement<SearchbyCreaDateResponse> createSearchbyCreaDateResponse(SearchbyCreaDateResponse value) {
        return new JAXBElement<SearchbyCreaDateResponse>(_SearchbyCreaDateResponse_QNAME, SearchbyCreaDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "SearchbyTitleResponse")
    public JAXBElement<SearchbyTitleResponse> createSearchbyTitleResponse(SearchbyTitleResponse value) {
        return new JAXBElement<SearchbyTitleResponse>(_SearchbyTitleResponse_QNAME, SearchbyTitleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyAuthorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "SearchbyAuthorResponse")
    public JAXBElement<SearchbyAuthorResponse> createSearchbyAuthorResponse(SearchbyAuthorResponse value) {
        return new JAXBElement<SearchbyAuthorResponse>(_SearchbyAuthorResponse_QNAME, SearchbyAuthorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyKeywords }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "SearchbyKeywords")
    public JAXBElement<SearchbyKeywords> createSearchbyKeywords(SearchbyKeywords value) {
        return new JAXBElement<SearchbyKeywords>(_SearchbyKeywords_QNAME, SearchbyKeywords.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchbyTitle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "SearchbyTitle")
    public JAXBElement<SearchbyTitle> createSearchbyTitle(SearchbyTitle value) {
        return new JAXBElement<SearchbyTitle>(_SearchbyTitle_QNAME, SearchbyTitle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyImageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://image.me.org/", name = "ModifyImageResponse")
    public JAXBElement<ModifyImageResponse> createModifyImageResponse(ModifyImageResponse value) {
        return new JAXBElement<ModifyImageResponse>(_ModifyImageResponse_QNAME, ModifyImageResponse.class, null, value);
    }

}
