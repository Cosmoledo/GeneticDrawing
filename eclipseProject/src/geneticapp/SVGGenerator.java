package geneticapp;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.CachedImageHandlerPNGEncoder;
import org.apache.batik.svggen.SVGGeneratorContext;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;

import geneticapp.Interface.CanvasPanel;

/*
 * Class for generateing SVGs
 * The method generateSVG generates a SVG from the graphics of a JPanel subclass
 * and writes it to the provided Outputstream
 */
public class SVGGenerator {
	private CachedImageHandlerPNGEncoder ihandler;

	public SVGGenerator() {
		try {
			this.ihandler = new CachedImageHandlerPNGEncoder("res/images", null);
		} catch (final SVGGraphics2DIOException ex) {
			ex.printStackTrace();
		}
	}

	public void generateSVG(final CanvasPanel myCanvas, final OutputStream outStream) {
		final String svgNS = "http://www.w3.org/2000/svg";
		final DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();
		final org.w3c.dom.Document myFactory = domImpl.createDocument(svgNS, "svg", null);
		final SVGGeneratorContext ctx = SVGGeneratorContext.createDefault(myFactory);
		ctx.setGenericImageHandler(this.ihandler);
		final SVGGraphics2D svgGenerator = new SVGGraphics2D(ctx, false);
		myCanvas.paintComponent(svgGenerator);
		Writer out = null;
		try {
			out = new OutputStreamWriter(outStream, "UTF-8");
		} catch (final UnsupportedEncodingException ex) {
			Logger.getLogger(SVGGenerator.class.getName()).log(Level.SEVERE, null, ex);
		}
		try {
			svgGenerator.stream(out, true);
		} catch (final SVGGraphics2DIOException ex) {
			Logger.getLogger(SVGGenerator.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}