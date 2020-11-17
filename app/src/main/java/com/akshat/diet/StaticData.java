package com.akshat.diet;

import java.util.ArrayList;

public class StaticData {

    public static ArrayList addExercise(ArrayList<StructureExercise> allExercises) {

        StructureExercise exercise;

        exercise = new StructureExercise("Plank", "Strengthens core", "Core", "exercise_plank", "60");
        allExercises.add(exercise);

        exercise = new StructureExercise("Bridge", "Strengthens core and backside", "Core", "exercise_bridge", "40");
        allExercises.add(exercise);

        exercise = new StructureExercise("Push-Up", "Strengthens core", "Chest, Triceps", "exercise_pushup", "30");
        allExercises.add(exercise);

        exercise = new StructureExercise("Squat", "Strengthens legs", "Legs", "exercise_squat", "120");
        allExercises.add(exercise);

        exercise = new StructureExercise("Incline Dumbbell\nBench Press", "Foucs on Upper peck", "Chest", "exercise_incline_press", "90");
        allExercises.add(exercise);

        exercise = new StructureExercise("Decline Dumbbell\nBench Press", "Foucs on Lower peck", "Chest", "exercise_decline_press", "45");
        allExercises.add(exercise);

        exercise = new StructureExercise("Pec Deck Machine", "Effective entire chest workout", "Chest", "exercise_pec_deck", "60");
        allExercises.add(exercise);

        exercise = new StructureExercise("Standing Barbell\nCurl", "Works on biceps", "Arms", "exercise_barbell", "45");
        allExercises.add(exercise);

        exercise = new StructureExercise("Close-grip\nBench Press", "Works on triceps", "Arms", "exercise_close_grip", "90");
        allExercises.add(exercise);

        exercise = new StructureExercise("Palms-up wrist\ncurl", "Works on forearms", "Arms", "exercise_up_curl", "60");
        allExercises.add(exercise);

        exercise = new StructureExercise("Palms-up wrist\ncurl", "Works on forearms", "Arms", "exercise_up_curl", "60");
        allExercises.add(exercise);

        return allExercises;
    }

    public static ArrayList addDiet(ArrayList<StructureDiet> allDiet) {
        StructureDiet diet;

        diet = new StructureDiet("Egg and Avocado Toast", "breakfast", "2 eggs:1 Toast:1/2 Avocado",
                "weight_loss", 400);
        allDiet.add(diet);

        diet = new StructureDiet("Pesto and Parmesan Eggs", "breakfast", "2 eggs:1/2 tbsp butter Toast:1 tbsp Pesto:1 tbsp grated Parmesan ",
                "weight_loss", 300);
        allDiet.add(diet);

        diet = new StructureDiet("Peanut Butter, Banana and Chia Toast", "breakfast",
                "1 whole grain toast:1tbsp peanut butter:1 sliced banana:2 tbsp Chia seeds",
                "weight_loss", 250);
        allDiet.add(diet);

        diet = new StructureDiet("Yogurt Sundae", "breakfast",
                "1/3 cup crushed raspberries:1tbsp maple syrup:1 cup 2-percent Greek yogurt:1/4 cup blueberries:  1 tbsp chopped roasted almonds",
                "weight_loss", 250);
        allDiet.add(diet);

        /////////////////////////////////////////////////////////////

        diet = new StructureDiet("Pesto and Parmesan Eggs", "lunch", "2 eggs:1/2 tbsp butter Toast:1 tbsp Pesto:1 tbsp grated Parmesan ",
                "weight_loss", 600);
        allDiet.add(diet);

        diet = new StructureDiet("Peanut Butter, Banana and Chia Toast", "lunch",
                "1 whole grain toast:1tbsp peanut butter:1 sliced banana:2 tbsp Chia seeds",
                "weight_loss", 750);
        allDiet.add(diet);

        diet = new StructureDiet("Egg and Avocado Toast", "lunch", "2 eggs:1 Toast:1/2 Avocado",
                "weight_loss", 500);
        allDiet.add(diet);


        diet = new StructureDiet("Yogurt Sundae", "lunch",
                "1/3 cup crushed raspberries:1tbsp maple syrup:1 cup 2-percent Greek yogurt:1/4 cup blueberries:  1 tbsp chopped roasted almonds",
                "weight_loss", 590);
        allDiet.add(diet);

        /////////////////////////////////////////////////////////////

        diet = new StructureDiet("Peanut Butter, Banana and Chia Toast", "dinner",
                "1 whole grain toast:1tbsp peanut butter:1 sliced banana:2 tbsp Chia seeds",
                "weight_loss", 250);
        allDiet.add(diet);

        diet = new StructureDiet("Yogurt Sundae", "dinner",
                "1/3 cup crushed raspberries:1tbsp maple syrup:1 cup 2-percent Greek yogurt:1/4 cup blueberries:  1 tbsp chopped roasted almonds",
                "weight_loss", 250);
        allDiet.add(diet);

        diet = new StructureDiet("Egg and Avocado Toast", "dinner", "2 eggs:1 Toast:1/2 Avocado",
                "weight_loss", 400);
        allDiet.add(diet);

        diet = new StructureDiet("Pesto and Parmesan Eggs", "dinner", "2 eggs:1/2 tbsp butter Toast:1 tbsp Pesto:1 tbsp grated Parmesan ",
                "weight_loss", 300);
        allDiet.add(diet);

        return allDiet;
    }

}
