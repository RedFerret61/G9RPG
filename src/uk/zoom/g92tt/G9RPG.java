/*
 * 
 */
package uk.zoom.g92tt;

/*
 * Clipboard example snippet: copy and paste data with the clipboard
 *
 * For a list of all SWT example snippets see
 * http://dev.eclipse.org/viewcvs/index.cgi/%7Echeckout%7E/platform-swt-home/dev.html#snippets
 */

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.graphics.Image;


import java.io.InputStream;
//import uk.zoom.g92tt.G9Patch.EffectCollection;

//import uk.zoom.g92tt.G9Patch.AmpChain;

//import uk.zoom.g92tt.G9Patch.DelayKind;


public class G9RPG { 
	public static G9Patch patch = new G9Patch();
	private static int theNumFX = 3;
	
	private static G9Patch.ProductKind aProductKind = patch.getProductKind();
	private static G9Patch.EffectCollection anEffectCollection = G9Patch.EffectCollection.Any_FX;
	private static G9Patch.AmpKind aAmpKind = patch.getAmpKind();
	private static G9Patch.ZNRKind aZNRKind = patch.getZNRKind();
	private static G9Patch.CompKind aCompKind = patch.getCompKind();
	private static G9Patch.WahKind aWahKind = patch.getWahKind();
	private static G9Patch.CabiKind aCabiKind = patch.getCabiKind();
	private static G9Patch.ModKind aModKind = patch.getModKind();
	private static G9Patch.DelayKind aDelayKind = patch.getDelayKind();
	private static G9Patch.RevKind aRevKind = patch.getRevKind();
	private static G9Patch.ARRMKind aARRMKind = patch.getARRMKind();
	private static G9Patch.PedalVolumeKind aPedalVolumeKind = patch.getPedalVolumeKind();
	private static String s;
	
	public static void main(String[] args) {
		
		Display display = new Display();
		final Clipboard cb = new Clipboard(display);
		final Shell shell = new Shell(display);
		shell.setText("Zoom Random Patch Generator 0.25");
		// icon for task bar
		InputStream imageStream = G9RPG.class.getClassLoader().getResourceAsStream("uk/zoom/g92tt/images/g9rpg.jpg");
		shell.setImage(new Image(display, imageStream));
		
		shell.setLayout(new FormLayout());
		final Text text = new Text(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL
				| SWT.H_SCROLL);
		
		final Label productLabel;
		final Combo productCombo;
		final Label theNumFXlabel;
		final Combo theNumFXCombo;
		
		//final Label delayLabel;
		final Combo ampCombo;
		final Label eqlabel;
		final Combo eqCombo;
		final Combo znrCombo;
		final Combo compCombo;
		final Combo wahCombo;
		final Combo cabiCombo;
		final Combo modCombo;
		final Combo delayCombo;
		final Combo revCombo;
		final Combo EffectCollectionCombo;		
		final Combo arrmCombo;
		final Combo pedalVolumeCombo;
		final Label pedalTargetslabel;
		final Combo pedalTargetsCombo;
		
		String[] newItems;
		
		
		Button generate = new Button(shell, SWT.PUSH);
		generate.setText("Generate");
		generate.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				// String textData = text.getSelectionText();
				
				patch.generate();
				
				// generate until number of desired effects are correct
				int tries = 0;
				while (patch.getEffectsOn() != theNumFX ) 
					{patch.generate();
					tries++;
					if (tries > 1000) break;
					}
				
				// when satisied with patch generate the patch name/comment 
				patch.genPatchName();
				patch.genPatchComment();
				
				// put Patch text on clipboard
				String textData = patch.toString();
				TextTransfer textTransfer = TextTransfer.getInstance();
				cb.setContents(new Object[] { textData },
						new Transfer[] { textTransfer });
				// output generated patch text data
				if (textData != null) {
					text.insert(textData);
					// mac does not automatically scroll to end of text,
					// so as a workaround set insertion point back to beginning
					// note this uses up quite a lot of CPU to shift text down
					text.setSelection(0);
					// the folowing on its own does not fix mac not displaying latest patch bug
					// text.showSelection();
				}
				
			}
		});
		
	
		
		// Combo to change theEffectCollection	     
		EffectCollectionCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		EffectCollectionCombo.setItems(new String[] { 
				"G7_FX_only", "G9_extra_FX", "Any_FX"
		});
		EffectCollectionCombo.select(EffectCollectionCombo.indexOf("Any_FX"));
		EffectCollectionCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				
				// when Any_FX menu is changed then call setEffectsCollection(selectedFXmenuitem).   
				String s = EffectCollectionCombo.getText();
				//convert string to enum
				anEffectCollection = G9Patch.EffectCollection.valueOf(s);
				patch.setEffectCollection( anEffectCollection );
			}
		});
		
		pedalVolumeCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		pedalVolumeCombo.setItems(new String[] { 
				"PedalVolume_Off", "PedalVolume_On"
		});
		pedalVolumeCombo.select(pedalVolumeCombo.indexOf("PedalVolume_On"));
		pedalVolumeCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the pedalVolume kind
				
				String s = pedalVolumeCombo.getText();
				//convert string to enum
				aPedalVolumeKind = G9Patch.PedalVolumeKind.valueOf(s);
				patch.setPedalVolumeKind( aPedalVolumeKind );
			}
		});
		
		// Combo to change product
		productLabel=new Label(shell,SWT.NONE);
		productLabel.setText("Console:");
		
		productCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		productCombo.setItems(new String[] { 
				"G71ut", "G92tt"
		});
		productCombo.select(productCombo.indexOf("G92tt"));
		productCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the product kind
				
				s = productCombo.getText();
				//convert string to enum
				aProductKind = G9Patch.ProductKind.valueOf(s);
				patch.setProductKind( aProductKind );
				
				switch (aProductKind) {
				case G92tt:
					// when Effects Processor selected is G92tt then 
					// enable EffectCollectionCombo setEffectsCollection(selectedFXmenuitem) , 
					EffectCollectionCombo.select(EffectCollectionCombo.indexOf("Any_FX"));
					s = EffectCollectionCombo.getText();
					//convert string to enum
					anEffectCollection = G9Patch.EffectCollection.valueOf(s);
					patch.setEffectCollection( anEffectCollection );
					EffectCollectionCombo.setEnabled(true);
					// for G9 default is pedal1 on setting volume   
					pedalVolumeCombo.select(pedalVolumeCombo.indexOf("PedalVolume_On"));
					s = pedalVolumeCombo.getText();
					//convert string to enum
					aPedalVolumeKind = G9Patch.PedalVolumeKind.valueOf(s);
					patch.setPedalVolumeKind( aPedalVolumeKind );
					break;
				case G71ut:
					// when G71ut is selected then 
					// G7_FX_only is displayed, call setEffectsCollection(G7_FX_only) and 
					// EffectCollectionCombo is greyed out, 
					EffectCollectionCombo.select(EffectCollectionCombo.indexOf("G7_FX_only"));
					s = EffectCollectionCombo.getText();
					//convert string to enum
					anEffectCollection = G9Patch.EffectCollection.valueOf(s);
					patch.setEffectCollection( anEffectCollection );
					EffectCollectionCombo.setEnabled(false);
					// for G7 don't waste the one pedal on setting volume   
					pedalVolumeCombo.select(pedalVolumeCombo.indexOf("PedalVolume_Off"));
					s = pedalVolumeCombo.getText();
					//convert string to enum
					aPedalVolumeKind = G9Patch.PedalVolumeKind.valueOf(s);
					patch.setPedalVolumeKind( aPedalVolumeKind );
					
					break;
				}
				
			}
		});
		
		// Minimum Number of Effects
		theNumFXlabel=new Label(shell,SWT.NONE);
		theNumFXlabel.setText("# major FX desired:");
		
		// Combo to change theNumFX value. 
		// see http://www.java2s.com/Code/Java/SWT-JFace-Eclipse/ImageAnalyzerinSWT.htm
		theNumFXCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		for (int i = 1; i <= 5; i += 1) {
			theNumFXCombo.add(String.valueOf(i));
		}
		theNumFXCombo.select(theNumFXCombo.indexOf("3"));
		theNumFXCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					theNumFX = Integer.parseInt(theNumFXCombo.getText());
				} catch (NumberFormatException e) {
					theNumFXCombo.select(theNumFXCombo.indexOf("3"));
					theNumFX = 3;
				}
			}
		}
		);
		
		
		// Combo to change the amp
		ampCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		ampCombo.setItems(new String[] { 
				"Acoustic", "Clean_Amp", "Mild_Distortion", "Heavy_Distortion", "Extreme_Distortion", "Any_Amp"
		});
		ampCombo.select(ampCombo.indexOf("Any_Amp"));
		ampCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the amp kind
				
				String s = ampCombo.getText();
				//convert string to enum
				aAmpKind = G9Patch.AmpKind.valueOf(s);
				patch.setAmpKind( aAmpKind );
			}
		});
		
		// EQ Range
		eqlabel=new Label(shell,SWT.NONE);
		eqlabel.setText("EQ - maximum boost/cut:");
		
		eqCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		for (int i = 0; i <= 12; i += 1) {
			eqCombo.add(String.valueOf(i));
		}
		eqCombo.select(eqCombo.indexOf(String.valueOf(patch.getEQRange())));
		eqCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					patch.setEQRange(Integer.parseInt(eqCombo.getText()));
				} catch (NumberFormatException e) {
					eqCombo.select(eqCombo.indexOf("6"));
					patch.setEQRange(6);
				}
			}
		}
		);
		
		// Combo to change the znr
		znrCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		znrCombo.setItems(new String[] { 
				"ZNR_Off", "ZNR_On", "Any_ZNR"
		});
		znrCombo.select(znrCombo.indexOf("ZNR_On"));
		znrCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the znr kind
				
				String s = znrCombo.getText();
				//convert string to enum
				aZNRKind = G9Patch.ZNRKind.valueOf(s);
				patch.setZNRKind( aZNRKind );
			}
		});
		
		// Combo to change the comp
		compCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		G9Patch.CompKind[] compValues = G9Patch.CompKind.values(); // get an array of the enum
		newItems= new String[compValues.length];
		for(int i = 0; i < compValues.length; i++) {
               newItems[i]= compValues[i].toString();		
            }
		
		compCombo.setItems(newItems);
		compCombo.select(compCombo.indexOf(patch.getCompKind().toString()));
		compCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the comp kind
				
				String s = compCombo.getText();
				//convert string to enum
				aCompKind = G9Patch.CompKind.valueOf(s);
				patch.setCompKind( aCompKind );
			}
		});
		
		
		// Combo to change the wah
		// 
		wahCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		G9Patch.WahKind[] wahValues = G9Patch.WahKind.values(); // get an array of the enum
		newItems= new String[wahValues.length];
		for(int i = 0; i < wahValues.length; i++) {
               newItems[i]= wahValues[i].toString();		
		}
		
		wahCombo.setItems(newItems);
		wahCombo.select(wahCombo.indexOf("Any_Wah"));
		wahCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the wah kind
				
				String s = wahCombo.getText();
				//convert string to enum
				aWahKind = G9Patch.WahKind.valueOf(s);
				patch.setWahKind( aWahKind );
			}
		});
		
		// Combo to change the cabi
		cabiCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		G9Patch.CabiKind[] cabiValues = G9Patch.CabiKind.values(); // get an array of the enum
		newItems= new String[cabiValues.length];
		for(int i = 0; i < cabiValues.length; i++) {
               newItems[i]= cabiValues[i].toString();		
            }
		
		cabiCombo.setItems(newItems);
		cabiCombo.select(cabiCombo.indexOf(patch.getCabiKind().toString()));
		cabiCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the cabi kind
				
				String s = cabiCombo.getText();
				//convert string to enum
				aCabiKind = G9Patch.CabiKind.valueOf(s);
				patch.setCabiKind( aCabiKind );
			}
		});
		
		// Combo to change the mod
		modCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		G9Patch.ModKind[] modValues = G9Patch.ModKind.values(); // get an array of the enum
		newItems= new String[modValues.length];
		for(int i = 0; i < modValues.length; i++) {
               newItems[i]= modValues[i].toString();		
            }
		
		modCombo.setItems(newItems);
		modCombo.select(modCombo.indexOf("Any_Mod"));
		modCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the mod kind
				
				String s = modCombo.getText();
				//convert string to enum
				aModKind = G9Patch.ModKind.valueOf(s);
				patch.setModKind( aModKind );
			}
		});
		
	
		// Combo to change the delay
		delayCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		G9Patch.DelayKind[] delayValues = G9Patch.DelayKind.values(); // get an array of the enum
		newItems= new String[delayValues.length];
		for(int i = 0; i < delayValues.length; i++) {
               newItems[i]= delayValues[i].toString();		
            }
		
		delayCombo.setItems(newItems);
		delayCombo.select(delayCombo.indexOf(patch.getDelayKind().toString()));
		delayCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the delay kind
				
				String s = delayCombo.getText();
				//convert string to enum
				aDelayKind = G9Patch.DelayKind.valueOf(s);
				patch.setDelayKind( aDelayKind );
			}
		});
		
		// Combo to change the rev
		revCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		
		G9Patch.RevKind[] revValues = G9Patch.RevKind.values(); // get an array of the enum
		newItems= new String[revValues.length];
		for(int i = 0; i < revValues.length; i++) {
               newItems[i]= revValues[i].toString();		
            }
		
		revCombo.setItems(newItems);
		revCombo.select(revCombo.indexOf(patch.getRevKind().toString()));
		revCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the rev kind
				
				String s = revCombo.getText();
				//convert string to enum
				aRevKind = G9Patch.RevKind.valueOf(s);
				patch.setRevKind( aRevKind );
			}
		});
		
		arrmCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		arrmCombo.setItems(new String[] { 
				"ARRM_Off", "ARRM_On"
		});
		arrmCombo.select(arrmCombo.indexOf("ARRM_Off"));
		arrmCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				// set the arrm kind
				
				String s = arrmCombo.getText();
				//convert string to enum
				aARRMKind = G9Patch.ARRMKind.valueOf(s);
				patch.setARRMKind( aARRMKind );
			}
		});
		
		
		
		// Number of PedalTargets
		pedalTargetslabel=new Label(shell,SWT.NONE);
		pedalTargetslabel.setText("Number of Pedal Targets:");
		
		pedalTargetsCombo = new Combo(shell, SWT.DROP_DOWN | SWT.READ_ONLY);
		for (int i = 1; i <= 4; i += 1) {
			pedalTargetsCombo.add(String.valueOf(i));
		}
		pedalTargetsCombo.select(pedalTargetsCombo.indexOf(String.valueOf(patch.getPedalTargets())));
		pedalTargetsCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					patch.setPedalTargets(Integer.parseInt(pedalTargetsCombo.getText()));
				} catch (NumberFormatException e) {
					pedalTargetsCombo.select(pedalTargetsCombo.indexOf("1"));
					patch.setPedalTargets(1);
				}
			}
		}
		);
		
		Button paste = new Button(shell, SWT.PUSH);
		paste.setText("Paste");
		paste.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TextTransfer transfer = TextTransfer.getInstance();
				String data = (String) cb.getContents(transfer);
				if (data != null) {
					text.insert(data);
					// mac does not automatically scroll to end of text,
					// so as a workaround set insertion point back to beginning
					// note this uses up quite a lot of CPU to shift text down
					text.setSelection(0);
					// the folowing on its own does not fix mac not displaying latest patch bug
					// text.showSelection();
					try {
						patch.paste(data);
						// now get generate settings from pasted patch
					}
					catch (IllegalArgumentException ex){
 					 
						System.err.println("Unable to paste supplied patch data.");
//						 clean up patch object
						patch.generate();
					}
					finally {
						
					 }
					// change generate settings based on patch
					// get the ZNRKind and display it 
					
					aProductKind = patch.getProductKind();
					productCombo.select(productCombo.indexOf(patch.getProductKind().toString()));
					
					switch (aProductKind) {
					case G92tt:
						// when Effects Processor selected is G92tt then 
						// enable EffectCollectionCombo setEffectsCollection(selectedFXmenuitem) , 
						EffectCollectionCombo.select(EffectCollectionCombo.indexOf("Any_FX"));
						s = EffectCollectionCombo.getText();
						//convert string to enum
						anEffectCollection = G9Patch.EffectCollection.valueOf(s);
						patch.setEffectCollection( anEffectCollection );
						EffectCollectionCombo.setEnabled(true);
							break;
					case G71ut:
						// when G71ut is selected then 
						// G7_FX_only is displayed, call setEffectsCollection(G7_FX_only) and 
						// EffectCollectionCombo is greyed out, 
						EffectCollectionCombo.select(EffectCollectionCombo.indexOf("G7_FX_only"));
						s = EffectCollectionCombo.getText();
						//convert string to enum
						anEffectCollection = G9Patch.EffectCollection.valueOf(s);
						patch.setEffectCollection( anEffectCollection );
						EffectCollectionCombo.setEnabled(false);

						
						break;
					}
					
					//  get theNumFX and display it 
					theNumFX = patch.getEffectsOn(); 
					theNumFXCombo.select(theNumFXCombo.indexOf("" + theNumFX));
					
					// get the AmpKind and display it 
					aAmpKind = patch.getAmpKind();
					ampCombo.select(ampCombo.indexOf(patch.getAmpKind().toString()));
					
					// get the EQRange and display it 
			        eqCombo.select(eqCombo.indexOf(String.valueOf(patch.getEQRange())));
					
					// get the ZNRKind and display it 
					aZNRKind = patch.getZNRKind();
					znrCombo.select(znrCombo.indexOf(patch.getZNRKind().toString()));
					
					// get the CompKind and display it 
					aCompKind = patch.getCompKind();
					compCombo.select(compCombo.indexOf(patch.getCompKind().toString()));
					
					// get the CabiKind and display it 
					aCabiKind = patch.getCabiKind();
					cabiCombo.select(cabiCombo.indexOf(patch.getCabiKind().toString()));
					
					// get the DelayKind and display it 
					aDelayKind = patch.getDelayKind();
					delayCombo.select(delayCombo.indexOf(patch.getDelayKind().toString()));
					
					// get the ModKind and display it 
					aModKind = patch.getModKind();
					modCombo.select(modCombo.indexOf(patch.getModKind().toString()));
					
					// get the WahKind and display it 
					aWahKind = patch.getWahKind();
					wahCombo.select(wahCombo.indexOf(patch.getWahKind().toString()));
					
					// get the RevKind and display it 
					aRevKind = patch.getRevKind();
					revCombo.select(revCombo.indexOf(patch.getRevKind().toString()));
					
					// get the ARRMKind and display it 
					aARRMKind = patch.getARRMKind();
					arrmCombo.select(arrmCombo.indexOf(patch.getARRMKind().toString()));
					
					// get the ARRMKind and display it 
					aPedalVolumeKind = patch.getPedalVolumeKind();
					pedalVolumeCombo.select(pedalVolumeCombo.indexOf(patch.getPedalVolumeKind().toString()));
					
					// get the PedalTargets and display 
					pedalTargetsCombo.select(pedalTargetsCombo.indexOf(String.valueOf(patch.getPedalTargets())));
					
					// now display parsed patch
					// put Patch text on clipboard
					String textData = patch.toString();
					TextTransfer textTransfer = TextTransfer.getInstance();
					cb.setContents(new Object[] { textData },
							new Transfer[] { textTransfer });
					// output generated patch text data
					if (textData != null) {
						text.insert(textData);
						// mac does not automatically scroll to end of text,
						// so as a workaround set insertion point back to beginning
						// note this uses up quite a lot of CPU to shift text down
						text.setSelection(0);
						// the folowing on its own does not fix mac not displaying latest patch bug
						// text.showSelection();
					}
				}
			}
		});
		
		// A FormLayout allows you to specify the position of a control in terms of its edges.
		// Each FormData has fields that represent the top, left, right, and bottom edges of the control.
		// The constructor FormAttachment(int numerator, int denominator, int offset) 
		// creates a new form attachment that's used to attach the edge of a control to a position in its parent. 
		// e.g. the right edge of the list box is assigned the attachment new FormAttachment(4,5,3), 
		// attaching it to be 4/5 of the width of the parent plus an extra 3 pixels. 
		// Instead of fractions, percentages can be specified.
		//
		//The constructor FormAttachment(Control control, int offset) creates a new form attachment 
		// that's used to attach the edge of a control to the edge of a sibling. 
		// e.g. the combo box attaches its left edge to be 5 pixels from the right edge of the slider 
		// using the attachment FormAttachment(slider,5).
		// 
		// generate layout
		FormData data = new FormData();
		data.right = new FormAttachment(100, -5); // 100% right of parent, less 5 pixels
		data.top = new FormAttachment(0, 5);      // 0% top of parent, plus 5 pixels
		generate.setLayoutData(data);
		

		
		// text area layout
		data = new FormData();
		data.left = new FormAttachment(0, 5);
		data.top = new FormAttachment(0, 5);
		// right side will lie in the same position as the right side of the generate control.
		data.right = new FormAttachment(generate, -100);
		data.bottom = new FormAttachment(100, -5);
		text.setLayoutData(data);


		// combo productCombo  layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(generate , 0);
		productCombo.setLayoutData(data);
		
		// productlabel  layout
		data = new FormData();
		data.right = new FormAttachment(productCombo, -5); // put combo to right of this one
		data.top = new FormAttachment(productCombo , -20); // put top -20 rom the top of this combo
		productLabel.setLayoutData(data);
		
		// combo EffectCollection layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(productCombo , 5);
		EffectCollectionCombo.setLayoutData(data);
		
		// minFXlabel  layout
		data = new FormData();
		data.right = new FormAttachment (100, -5);   // 100% right, -5 pixels from right edge
		//data.top = new FormAttachment(paste, 0);
		data.top = new FormAttachment(EffectCollectionCombo, 0);
		theNumFXlabel.setLayoutData(data);
		
		// combo minFXCombo  layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(theNumFXlabel , 5);
		theNumFXCombo.setLayoutData(data);
		
		// combo amp layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(theNumFXCombo , 5);
		ampCombo.setLayoutData(data);
		
		// eqlabel layout
		data = new FormData();
		data.right = new FormAttachment (100, -5);   // 100% right, -5 pixels from right edge
		//data.top = new FormAttachment(paste, 0);
		data.top = new FormAttachment(ampCombo, 0);
		eqlabel.setLayoutData(data);
		
		// eqCombo  layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(eqlabel , 5);
		eqCombo.setLayoutData(data);
		
		// combo znr layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(eqCombo , 5);
		znrCombo.setLayoutData(data);
		
		// combo comp layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(znrCombo , 5);
		compCombo.setLayoutData(data);
		
		// combo wah layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(compCombo , 5);
		wahCombo.setLayoutData(data);
		
		// combo cabi layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(wahCombo , 5);
		cabiCombo.setLayoutData(data);
		
		// combo mod layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(cabiCombo , 5);
		modCombo.setLayoutData(data);
		
		// delayLabel  layout
		//data = new FormData();
		//data.right = new FormAttachment (100, -5);   // 100% right, -5 pixels from right edge
		//data.top = new FormAttachment(modCombo, 0);
		//delayLabel.setLayoutData(data);
		
		// combo delay layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(modCombo , 5);
		delayCombo.setLayoutData(data);

		// delaylabel  layout
		//data = new FormData();
		//data.right = new FormAttachment(delayCombo, -5); // put combo to right of this one
		//data.top = new FormAttachment(delayCombo , -20); // put top -20 rom the top of this combo
		//delayLabel.setLayoutData(data);
		
		// combo rev layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(delayCombo , 5);
		revCombo.setLayoutData(data);
		
		// combo arrm layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(revCombo , 5);  
		arrmCombo.setLayoutData(data);
		
		// combo PedalVolume layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(arrmCombo , 5);
		pedalVolumeCombo.setLayoutData(data);
		
		// pedalTargetslabel layout
		data = new FormData();
		data.right = new FormAttachment (100, -5);   // 100% right, -5 pixels from right edge
		//data.top = new FormAttachment(paste, 0);
		data.top = new FormAttachment(pedalVolumeCombo, 0);
		pedalTargetslabel.setLayoutData(data);
		
		// pedalTargetsCombo  layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(pedalTargetslabel , 5);
		pedalTargetsCombo.setLayoutData(data);

        // paste layout
		data = new FormData();
		data.right = new FormAttachment(100, -5);
		data.top = new FormAttachment(pedalTargetsCombo, 5); // 5 pixels from the top edge of generate
		paste.setLayoutData(data);
		
		
		// shell.setSize(450, 790); // 10 text lines too long 
		shell.setSize(450, 640);
		shell.open();
		
		// initalise the patch object
		patch.generate();
		 
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		cb.dispose();
		display.dispose();
		// swtImage.dispose(); // icon
	}
}
