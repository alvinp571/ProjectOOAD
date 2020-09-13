package views.base;

public interface IView {
	
	public void initializeForm(String title);

	public Integer closeOperation();

	public void setLocation();

	public void render();

	public void showForm();

	public void initializeComponent();

	public void addComponent();

	public void addListener();
	
}
