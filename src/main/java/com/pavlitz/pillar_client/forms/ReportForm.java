package com.pavlitz.pillar_client.forms;

import com.pavlitz.pillar_client.mainView.ViewController;
import com.pavlitz.pillar_client.services.Phrase;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public class ReportForm extends VerticalLayout{
    private final ViewController viewController;
    private Button exit;
    private Grid<Phrase> grid;

    public ReportForm(ViewController viewController) {
        this.viewController = viewController;
        this.grid = new Grid<>(Phrase.class);
        this.add(btnBar());
    }

    private void fillGrid(List<Phrase> phrases){
        grid = new Grid<>(Phrase.class);
        grid.setItems(phrases);
        grid.setColumns("creationDate", "answerBody", "pillarType");
        add(grid);
        System.out.println("filled");
    }

    private HorizontalLayout btnBar() {
        HorizontalLayout layout = new HorizontalLayout();
        for (PillarType t : PillarType.values()) {
            ReportBtn r = new ReportBtn(t);
            r.addClickListener(buttonClickEvent -> {
                List<Phrase> phrases= viewController.getPhrasesForWeek(t);
                fillGrid(phrases);
            });
            layout.add(r);
        }
        exit = exitBtn();
        layout.add(exit);
        return layout;
    }

    private Button exitBtn(){
        Button b = new Button("Exit");
        b.addClickListener(buttonClickEvent -> {
            remove(grid);
            viewController.showMainForm();
        });
        return b;
    }

    private static class ReportBtn extends Button {
        private final PillarType type;

        public ReportBtn(PillarType type) {
            super(type.getName());
            this.type = type;
        }

        public PillarType getType() {
            return type;
        }

    }
}
