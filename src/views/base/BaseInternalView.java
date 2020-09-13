package views.base;

import static env.Env.getEnv;

import javax.swing.JInternalFrame;

import env.Environment;

public abstract class BaseInternalView extends JInternalFrame implements IView {
	
	private static final long serialVersionUID = 1L;

	public BaseInternalView(String title, Integer width, Integer height) {
		setSize(width, height);
		initializeForm(title);
	}

	@Override
	public void initializeForm(String title) {
		setTitle(title);
		setDefaultCloseOperation(closeOperation());
		setLocation();
	}

	@Override
	public Integer closeOperation() {
		return DISPOSE_ON_CLOSE;
	}

	@Override
	public void setLocation() {
		Integer mainWidth = new Integer(System.getProperty(getEnv(Environment.MAIN_WIDTH)));
		Integer mainHeight = new Integer(System.getProperty(getEnv(Environment.MAIN_HEIGHT)));
		setLocation(getCenter(mainWidth, getWidth()), getCenter(mainHeight, getHeight()));
	}

	@Override
	public void render() {
		initializeComponent();
		addComponent();
		addListener();
	}

	@Override
	public void showForm() {
		render();
	    setVisible(Boolean.TRUE);
	    setResizable(Boolean.FALSE);
	    setClosable(Boolean.TRUE);
	}

	private Integer getCenter(Integer a, Integer b) {
		return Math.abs(a - b) / 2;
	}
	
}
