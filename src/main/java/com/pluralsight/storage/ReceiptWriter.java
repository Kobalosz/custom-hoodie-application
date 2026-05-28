package com.pluralsight.storage;

import com.pluralsight.DTOs.ReceiptItemDTO;
import com.pluralsight.views.Receipt;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReceiptWriter {

    private static final String RECEIPTS_DIRECTORY =
            "receipts";

    public static void writeReceipt(
            Receipt receipt
    ) {

        try {

            // Create receipts directory if missing
            Files.createDirectories(
                    Path.of(RECEIPTS_DIRECTORY)
            );

            String fileName =
                    receipt.receiptNumber()
                            + ".csv";

            Path filePath =
                    Path.of(
                            RECEIPTS_DIRECTORY,
                            fileName
                    );

            try (
                    BufferedWriter writer =
                            new BufferedWriter(
                                    new FileWriter(
                                            filePath.toFile()
                                    )
                            )
            ) {


// This is the meta data for the receipt thought it'd be cool to have the date and time
                writer.write(
                        "Receipt Number,"
                                + receipt.receiptNumber()
                );

                writer.newLine();

                writer.write(
                        "Date,"
                                + receipt.createdAt()
                );

                writer.newLine();

                writer.write(
                        "Total,"
                                + String.format(
                                "%.2f",
                                receipt.total()
                        )
                );

                writer.newLine();
                writer.newLine();

// Description

                writer.write(
                        "Description|Price"
                );

                writer.newLine();

// Items

                for (
                        ReceiptItemDTO item
                        : receipt.items()
                ) {

                    writer.write(
                            escapeCsv(
                                    item.description()
                            )
                                    + "|"
                                    + String.format(
                                    "%.2f",
                                    item.price()
                            )
                    );

                    writer.newLine();
                }
            }

        } catch (IOException e) {

            throw new RuntimeException(
                    "Failed to write receipt.",
                    e
            );
        }
    }


    // ────────────────────────────
    // CSV escaping
    // ────────────────────────────

    private static String escapeCsv(
            String text
    ) {

        if (
                text.contains("|")
                        || text.contains("\"")
        ) {

            return "\""
                    + text.replace(
                    "\"",
                    "\"\""
            )
                    + "\"";
        }

        return text;
    }
}
